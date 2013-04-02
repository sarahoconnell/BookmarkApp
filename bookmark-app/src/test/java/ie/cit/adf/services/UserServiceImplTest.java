package ie.cit.adf.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import ie.cit.adf.domain.User;
import ie.cit.adf.domain.dao.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class UserServiceImplTest {

	private UserServiceImpl tested;
	private UserRepository userRepository;

	@Before 
	public void setup() {
		userRepository = Mockito.mock(UserRepository.class);
		tested = new UserServiceImpl();
		tested.repo = userRepository;
	}

	@Test
	public void testCreateUser() {  
		User newUser = tested.create("Sarah", "password", "tweetid");
		Mockito.verify(userRepository).create(newUser);
		assertThat(newUser.getName(), equalTo("Sarah"));
		assertThat(newUser.getPassword(), equalTo("password"));
		assertThat(newUser.getTwitterId(), equalTo("tweetid"));
	}
	
	@Test
	public void testGetAll() {  
		fail("Not implemented");
	}
	

	@Test
	public void testGet() {  
		fail("Not implemented");
	}


	@Test
	public void testUpdate() {  
		fail("Not implemented");
	}


	@Test
	public void testDelete() {  
		fail("Not implemented");
	}
}
