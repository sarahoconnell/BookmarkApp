package ie.cit.adf.integration;

import static org.junit.Assert.assertTrue;
import ie.cit.adf.constants.Constants;
import ie.cit.adf.web.LoginController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.ModelMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/system-test-context.xml")

public class LoginIntegrationTests extends AuthenticationHelper{
	
	@Autowired 
	private LoginController loginController;
	
	@Test(expected = BadCredentialsException.class)
	public void testBadLogin(){
		ModelMap model = new ExtendedModelMap();
		//login and check that you are directed to admin.jsp 
		login("fail", "fail");
		String page = loginController.welcome(model);
		assertTrue(page.equals(Constants.adminMapping));
	}
	
	@Test
	public void testLoginAdmin(){
		ModelMap model = new ExtendedModelMap();
		//login and check that you are directed to admin.jsp 
		login("admin", "password");
		String page = loginController.welcome(model);
		assertTrue(page.equals(Constants.adminMapping));
	}
	
	@Test
	public void testLoginUser(){
		ModelMap model = new ExtendedModelMap();
		//login and check that you are directed to admin.jsp 
		login("user", "password");
		String page = loginController.welcome(model);
		assertTrue(page.equals(Constants.dashboardMapping));
	}
	
	
}
