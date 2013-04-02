package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.User;
import ie.cit.adf.domain.UserRole;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository("hibernateUserRepository")
public class HibernateUserRepository implements UserRepository {


    @Autowired
    private SessionFactory sf;

    private String getLoggedInUser() {
	return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    
    public User get(String id) {
    	return (User) session().get(User.class, id);
    }
    
	@Override
	public void create(User user) {
		session().save(user);
	}
	

	@Override
	public void createRole(UserRole userRole) {
		session().save(userRole);
	}

	@Override
	public void delete(User user) {
		session().delete(user);
	}

	@Override
	public void update(User user) {
		session().merge(user);
	}


	@Override
	public User findById(String id) {
		return get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
    public Collection<User> findAll() {
    	return session().createQuery("from User").list();
    }

	@SuppressWarnings("unchecked")
	@Override
	public User findByName(String name) {
		Query query = session().createQuery("from User where name = :name");
		query.setParameter("name", name);
		return (User)query.uniqueResult();
	}
	
	 private Session session() {
			return sf.getCurrentSession();
		    }

}
