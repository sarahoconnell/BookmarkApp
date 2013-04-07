package ie.cit.adf.services;

import ie.cit.adf.domain.User;

import java.util.Collection;

public interface UserService {

   // GET {id}
   User findById(String userId);
   
   User findByName(String name);
		
	// GET
	Collection<User> findAll();

	// POST
	User create(String name, String password, String twitterId);
	void createRole(User user, String role);
	
	// PUT
	User update(User user);
	
	// DELETE {id}
	void delete(User user);

}
