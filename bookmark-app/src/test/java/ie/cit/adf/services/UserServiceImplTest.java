package ie.cit.adf.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import ie.cit.adf.domain.User;
import ie.cit.adf.domain.dao.UserRepository;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class UserServiceImplTest {

	private UserServiceImpl userService;
	private UserRepository userRepository;

	@Before 
	public void setup() {
		userRepository = Mockito.mock(UserRepository.class);
		userService = new UserServiceImpl();
		userService.repo = userRepository;
	}

	@Test
	public void testCreateUser() {  
		User newUser = userService.create("Sarah", "password", "tweetid");
		Mockito.verify(userRepository).create(newUser);
		assertThat(newUser.getName(), equalTo("Sarah"));
		assertThat(newUser.getPassword(), equalTo("password"));
		assertThat(newUser.getTwitterId(), equalTo("tweetid"));
		assertThat(newUser.getEnabled(), equalTo(true));
		
	}
	
	@Test
	public void testGetAll() {  
		//Expect that two users will be returned. 
		User newUser = userService.create("Sarah", "password", "");
		User newUser2 = userService.create("John", "password", "@john");
		User notIncluded = userService.create("Sally", "mypassword", "@sally");
		ArrayList<User> users = new ArrayList<User>();
		users.add(newUser);
		users.add(newUser2);
		Mockito.when(userRepository.findAll()).thenReturn(users);

		int numberOfUsers = userService.findAll().size();
		assertEquals(numberOfUsers, 2);

		assertEquals(userService.findAll().contains(newUser), true);
		
		assertEquals(userService.findAll().contains(newUser2), true);
		
		assertEquals(userService.findAll().contains(notIncluded), false);
		//ensure that the findAll method in userRepostory was called 4 times
		Mockito.verify(userRepository, Mockito.times(4)).findAll();
	}
	

	@Test
	public void testGet() {  
		User sarah = userService.create("Sarah", "password", "");
		User john = userService.create("John", "password", "@john");
		User notIncluded = userService.create("Sally", "mypassword", "@sally");
		
		Mockito.when(userService.findById(sarah.getId())).thenReturn(sarah);
		Mockito.when(userService.findById(john.getId())).thenReturn(john);
		Mockito.when(userService.findById(notIncluded.getId())).thenReturn(null);
		
		assertEquals(userService.findById(sarah.getId()), sarah);
		assertEquals(userService.findById(john.getId()), john);
		assertEquals(userService.findById(notIncluded.getId()), null);
		assertEquals(userService.findById("random"), null);
	
		//ensure that the find method in userRepostory was called the correct amount of times
		Mockito.verify(userRepository, Mockito.times(1)).findById(sarah.getId());
		Mockito.verify(userRepository, Mockito.times(1)).findById(john.getId());
		Mockito.verify(userRepository, Mockito.times(1)).findById(notIncluded.getId());
		Mockito.verify(userRepository, Mockito.times(1)).findById("random");

	
	}


	@Test
	public void testUpdate() {  
		User sarah = userService.create("Sarah", "password", "");
		User john = userService.create("John", "password", "@john");
		
		sarah.setPassword("newpassword");
		userService.update(sarah);
		Mockito.verify(userRepository, Mockito.times(1)).update(sarah);

		assertEquals(sarah.getPassword().equals("password"), false);
		assertEquals(sarah.getPassword().equals("newpassword"), true);
		
		john.setName("Jack");
		userService.update(john);
		Mockito.verify(userRepository, Mockito.times(1)).update(john);
		assertEquals(john.getName().equals("John"), false);
		assertEquals(john.getName().equals("Jack"), true);
		
		john.setEnabled(false);
		userService.update(john);
		Mockito.verify(userRepository, Mockito.times(2)).update(john);
		assertEquals(john.getEnabled(), false);
		
		assertEquals(sarah.getEnabled(), true);
		sarah.setEnabled(true);
		userService.update(sarah);
		Mockito.verify(userRepository, Mockito.times(2)).update(sarah);
		assertEquals(sarah.getEnabled(), true);
		
		assertEquals(sarah.getTwitterId(), "");
		sarah.setTwitterId("@sarah");
		userService.update(sarah);
		Mockito.verify(userRepository, Mockito.times(3)).update(sarah);
		assertEquals(sarah.getTwitterId(), "@sarah");
		
		
		sarah.setId("newUniqueId");
		userService.update(sarah);
		Mockito.verify(userRepository, Mockito.times(4)).update(sarah);
		assertEquals(sarah.getId(), "newUniqueId");
	}


	@Test
	public void testDelete() {  
		User sarah = userService.create("Sarah", "password", "");
		userService.delete(sarah);
		Mockito.verify(userRepository).delete(sarah);
        
        
		
	}
}
