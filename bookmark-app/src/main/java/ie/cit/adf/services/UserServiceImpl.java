package ie.cit.adf.services;

import ie.cit.adf.domain.User;
import ie.cit.adf.domain.dao.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {
	private UserRepository repo;

	public UserServiceImpl(UserRepository repo) {
		this.repo = repo;
	}

	public List<User> getAll() {
		return repo.getAll();
	}

	public User create(String name, String password, String twitterId) {
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setTwitterId(twitterId);
		repo.add(user);
		return user;
	}


	public User get(String userId) {
		return repo.findById(userId);
	}

	public User update(String userId, String name, String password, String twitterId) {
		User user = repo.findById(userId);
		user.setName(name);
		user.setPassword(password);
		user.setTwitterId(twitterId);
		repo.update(user);
		return user;
		
	}

	@Override
	public void delete(String userId) {
		repo.delete(userId);
	}

}
