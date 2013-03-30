package ie.cit.adf.services;

import ie.cit.adf.domain.User;

import java.util.List;

public interface UserService {

	// GET
	List<User> getAll();

	// POST
	User create(String name, String password, String twitterId);

	// PUT
	User update(String userId, String name, String password, String twitterId);

	// GET {id}
	User get(String userId);

	// validate 
	User findUser(String name, String password);
	
	// DELETE {id}
	void delete(String userId);

}
