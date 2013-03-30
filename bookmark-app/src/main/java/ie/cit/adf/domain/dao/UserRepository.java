package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.User;

import java.util.List;

public interface UserRepository {


	User findByNamePassword(String name, String password);
	
	User findById(String id);

	List<User> getAll();

	void add(User user);

	void delete(String user);

	void update(User user);

}
