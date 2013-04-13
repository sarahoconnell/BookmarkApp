package ie.cit.adf.integration;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
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
	protected void login(Object principal, Object credentials, String userRole){
		SecurityContext securityContext = new SecurityContextImpl();
		UsernamePasswordAuthenticationToken authentication;
		if(userRole != null){
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			GrantedAuthority authority = new GrantedAuthorityImpl(userRole);
			authorities.add(authority);
			authentication = new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
		}
		else{
			authentication = new UsernamePasswordAuthenticationToken(principal, credentials);
		}
		
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
