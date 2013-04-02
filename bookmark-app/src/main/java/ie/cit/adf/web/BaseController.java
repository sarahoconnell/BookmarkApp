package ie.cit.adf.web;

import ie.cit.adf.domain.User;
import ie.cit.adf.services.UserService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {  

	@Autowired
	private UserService userService;
	
	public User loggedInUser;
	public Collection role;
	
	public boolean loggedIn()
	{
		// logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated()) // ROLE?
		{
			String userName = auth.getName();
			loggedInUser = userService.findByName(userName);
			if(loggedInUser==null)
				return false;
			role = auth.getAuthorities();
			return true;
		}
		return false;
	}
}
