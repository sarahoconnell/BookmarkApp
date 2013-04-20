package ie.cit.adf.web;

import ie.cit.adf.domain.User;
import ie.cit.adf.services.UserService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {  
	
	private static final String ADMIN_USER = "ROLE_ADMIN";
	@Autowired
	private UserService userService;
	
	public User loggedInUser;
	
	public boolean loggedIn()
	{
		// logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated()) // ROLE?
		{
			String userName = auth.getName();
			
			loggedInUser = userService.findByName(userName);
			if(loggedInUser==null)
				return false;
			//role = auth.getAuthorities();
			
			for(GrantedAuthority authority : auth.getAuthorities())
			{
				if(authority.getAuthority().equalsIgnoreCase(ADMIN_USER))
				{
		//			isAdmin = true;
				}
			}
			
			return true;			
		}
		return false;
	}
	
	public boolean isAdmin()
	{
		// logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated()) // ROLE?
		{
						
			for(GrantedAuthority authority : auth.getAuthorities())
			{
				if(authority.getAuthority().equalsIgnoreCase(ADMIN_USER))
				{
					return true;		
				}
			}
				
		}
		return false;
	}
}
