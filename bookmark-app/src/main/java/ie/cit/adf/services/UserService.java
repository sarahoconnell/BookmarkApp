package ie.cit.adf.services;

import ie.cit.adf.domain.User;

import java.util.Collection;

public interface UserService {

   // GET {id}
   User findById(String userId);

   // validate 
   User findByNamePassword(String name, String password);
   
   User findByName(String name);
		
	// GET
	Collection<User> findAll();

	// POST
	User create(String name, String password, String twitterId);
	void createRole(User user, String role);
	
	// PUT
	User update(String userId, String name, String password, String twitterId);
	
	// DELETE {id}
	void delete(User user);

}
