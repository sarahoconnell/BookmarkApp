package ie.cit.adf.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

public class AuthenticationHelper {
	@Autowired
	//private AuthenticationProvider provider;
	private DaoAuthenticationProvider provider;
	
	
	/**
	 * Logs in as the admin user
	 */
	protected void login(Object principal, Object credentials){
		SecurityContext securityContext = new SecurityContextImpl();
		UsernamePasswordAuthenticationToken authentication;
		
		authentication = new UsernamePasswordAuthenticationToken(principal, credentials);
		
		securityContext.setAuthentication(authentication);
		provider.authenticate(authentication);
		SecurityContextHolder.setContext(securityContext);
	}
	
	
	/** 
	 * Logs in as a normal user 
	 */
	protected void logout(){
		SecurityContextHolder.getContext().setAuthentication(null);
		
	}
	
	
}
