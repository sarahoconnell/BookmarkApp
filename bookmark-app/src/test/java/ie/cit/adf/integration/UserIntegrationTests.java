package ie.cit.adf.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.springframework.security.access.AccessDeniedException;

import ie.cit.adf.domain.User;
import ie.cit.adf.services.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/system-test-context.xml")

public class UserIntegrationTests extends AuthenticationHelper{
	
	@Autowired
	private UserService userService;
	
	@Test(expected = AuthenticationCredentialsNotFoundException.class)  
	public void testFindAllWithoutLogin(){
		logout();
		userService.findAll();
	}
	
	
	@Test(expected = AccessDeniedException.class)
	public void testFindAllWithUserLogin(){
		login("user", "password");
		userService.findAll();
	}
	
	@Test
	public void testFindAllWithAdminLogin(){
		login("admin", "password");
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
		login("admin", "password");
		assertThat(SecurityContextHolder.getContext().getAuthentication().getName(), equalTo("admin"));
	}
	
	@Test(expected = BadCredentialsException.class)
	public void failureAuthentication(){
		login("badUSer", "password");
		SecurityContextHolder.getContext().getAuthentication().getName();
		
	}
	
}
