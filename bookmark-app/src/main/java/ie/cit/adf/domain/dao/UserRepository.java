package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.User;
import ie.cit.adf.domain.UserRole;

import java.util.Collection;

public interface UserRepository {

	User get(String id);

    void create(User user);
    void createRole(UserRole role);

    void update(User user);
   
    void delete(User user);

    User findById(String id);
    User findByName(String name);
    Collection<User> findAll();

}
