package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Link;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("hibernateLinkRepository")
public class HibernateLinkRepository implements LinkRepository {


    @Autowired
    private SessionFactory sf;

    public Link get(String id) {
    	return (Link) session().get(Link.class, id);
    }
    
	@Override
	public void create(Link link) {
		session().save(link);
	}

	@Override
	public void delete(Link link) {
		session().delete(link);
	}

	@Override
	public void update(Link link) {
		session().merge(link);
	}


	@Override
	public Link findById(String id) {
		return get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
    public Collection<Link> findAll() {
    	return session().createQuery("from Link").list();
    }

	 private Session session() {
			return sf.getCurrentSession();
		    }

}
