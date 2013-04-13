package ie.cit.adf.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.User;
import ie.cit.adf.services.BoardService;
import ie.cit.adf.services.UserService;
import ie.cit.adf.web.UserController;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/system-test-context.xml")

public class UserIntegrationTests extends AuthenticationHelper{
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private UserController userController;
	
	@Autowired 
	private BoardService boardService;
	
	
	@Test(expected = AuthenticationCredentialsNotFoundException.class)  
	public void testFindAllWithoutLogin(){
		logout();
		userService.findAll();
	}
	
	
	@Test(expected = AccessDeniedException.class)
	public void testFindAllWithUserLogin(){
		login("user", "password", null);
		userService.findAll();
	}
	
	@Test
	public void testFindAllWithAdminLogin(){
		login("admin", "password", null);
		assertThat(userService.findAll().size(), equalTo(2));
		
	}
	
	@Test
	public void testUserCreation(){
		User user = userService.create("testuser", "password", "tweeter");
		//get the user id 
		String uid = user.getId();

		//find the user from the repository 
		User dbUser =  userService.findById(uid);
		assertThat(user.getName(), equalTo(dbUser.getName()));
	}
	
	@Test 
	public void testFindById(){
		User admin = userService.findById("007");
		assertThat(admin.getName(), equalTo("admin"));
		
		//test finding an invalid user 
		assertThat(userService.findById("nonexistent"), equalTo(null));
	}
	


	@Test
	public void successfulAuthentication(){
		login("admin", "password", null);
		assertThat(SecurityContextHolder.getContext().getAuthentication().getName(), equalTo("admin"));
	}
	
	@Test(expected = BadCredentialsException.class)
	public void failureAuthentication(){
		login("badUSer", "password", null);
		SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	@Test
	public void testFindUserByName(){
		login("admin", "password", null);
		User user = userService.findByName("user");
		assertThat(user.getName(), equalTo("user"));
		
		//test failing case
		user = userService.findByName("notthere");
		assertThat(user, equalTo(null));
	}
	
	@Test 
	public void testDeleteUser(){
		login("admin", "password", null);
		//create a new user 
		User user = userService.create("newuser","password", "id");
		String uid = user.getId();
		//ensure the user is created 
		user = userService.findById(uid);
		assertThat(user.getName(), equalTo("newuser"));
		//delete the user 
		userService.delete(user);
		user = userService.findById(uid);
		assertThat(user, equalTo(null));
		
	}
	
	@Test 
	public void testBlockUser(){
		//test the disable function 
		login("admin", "password", null);
		//create a new user 
		User user = userService.create("newuser","password", "id");
		String uid = user.getId();
		//ensure the user is created 
		user = userService.findById(uid);
		assertThat(user.getName(), equalTo("newuser"));
		//get the user to login 
		user.setEnabled(false);
		userService.update(user);
		
		user =  userService.findById(uid);
		assertThat(user.getEnabled(), equalTo(false));
		
		//delete the user to clean up 
		userService.delete(user);
	}
	
	@Test 
	public void testUpdateUserDetails(){
		login("admin", "password", null);
		User user = userService.create("temporaryUser","password", "twitter");
		String uid = user.getId();
		//change password 
		user.setPassword("newpassword");
		userService.update(user);
		//ensure it's changed 
		assertThat(userService.findById(uid).getPassword(), equalTo("newpassword"));

		//change twitter 
		user.setTwitterId("newname");
		userService.update(user);
		//ensure it's changed 
		assertThat(userService.findById(uid).getTwitterId(), equalTo("newname"));
		
		//delete the user to clean up 
		userService.delete(user);
	}
	
	@Test 
	public void testCreateAdminUser(){
		login("admin", "password", null);
		User user = userService.create("newadmin","password", "twitter");
		userService.createRole(user,"ROLE_ADMIN");
		logout();
		login("newadmin", "password", null);
		//ensure they can find all users 
		assertThat(userService.findAll().size(), equalTo(3));

		//delete the user to clean up 
		userService.delete(user);
	}
	
	@Test 
	public void testCreateDuplicateUser(){
		//ensure that the controller stops creation of duplicate users
		Model model = new ExtendedModelMap();
		userController.create("user","password", "password", "twitter", model);
		assertThat(model.containsAttribute("error"), equalTo(true));
		//ensure that password check is carried out 
		model = new ExtendedModelMap();
		userController.create("user","password", "password2", "twitter", model);
		assertThat(model.containsAttribute("error"), equalTo(true));
	}
	
	@Test 
	public void testSuccessfulUserRegistration(){
		Model model = new ExtendedModelMap();
		userController.create("sarah", "password", "password", "@sarah", model);
		//find the user using the service 
		User user = userService.findByName("sarah");
		assertThat(user.getName(), equalTo("sarah"));
		//ensure the user has one board created 
		Collection<Board> boards = boardService.findAllByUserId(user.getId());
		assertThat(boards.size(), equalTo(1));

		//delete the user to clean up 
		login("admin","password", null);
		userService.delete(user);
		
	}
	
	
	
}
