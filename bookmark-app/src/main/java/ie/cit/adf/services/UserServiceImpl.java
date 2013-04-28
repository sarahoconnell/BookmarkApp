package ie.cit.adf.services;

import ie.cit.adf.domain.User;
import ie.cit.adf.domain.UserRole;
import ie.cit.adf.domain.dao.UserRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Secured({"ROLE_USER", "ROLE_ADMIN"})
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("hibernateUserRepository")
	UserRepository repo;

	public User findById(String userId) {
		return repo.findById(userId);
	}

	public User findByName(String name) {
		return repo.findByName(name);
	}
	
	//SECURE THIS FOR ADMIN USERS ONLY 
	@Secured("ROLE_ADMIN")
	public Collection<User> findAll() {
		return repo.findAll();
	}

	public User create(String name, String password, String twitterId) {
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setTwitterId(twitterId);
		user.setEnabled(true);
		repo.create(user);
		return user;
	}

	public void createRole(User user, String role) {
		UserRole userRole = new UserRole();
		userRole.setUserId(user.getId());
		userRole.setAuthority(role);
		repo.createRole(userRole);
	}
	
	public User update(User user) {
		repo.update(user);
		return user;		
	}

	//SECURE THIS FOR ADMIN USERS ONLY 
	@Secured("ROLE_ADMIN")
	@Override
	public void delete(User user) {
		repo.delete(user);
	}

}
