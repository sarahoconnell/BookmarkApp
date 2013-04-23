package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Board;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("hibernateBoardRepository")
public class HibernateBoardRepository implements BoardRepository {


    @Autowired
    private SessionFactory sf;

    public Board get(String id) {
    	return (Board) session().get(Board.class, id);
    }
    
	@Override
	public void create(Board board) {
		session().save(board);
	}

	@Override
	public void delete(Board board) {
		session().delete(board);
	}

	@Override
	public void update(Board board) {
		session().merge(board);
	}


	@Override
	public Board findById(String id) {
		return get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
    public Collection<Board> findAll() {
    	return session().createQuery("from Board").list();
    }

	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Board> findAllByUserId(String userId) {
		Query query = session().createQuery("from Board where userId = :userId ");
		query.setParameter("userId", userId);
		return query.list();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Board> findAllPublicByUserId(String userId, boolean ispublic) {
		Query query = session().createQuery("from Board where userId = :userId and ispublic = :ispublic ");
		query.setParameter("userId", userId);
		query.setParameter("ispublic", ispublic);
		return query.list();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Board> findAllPublic() {
		Query query = session().createQuery("from Board where ispublic = :ispublic ");
		query.setParameter("ispublic", true);
		return query.list();
	}
	
	 private Session session() {
			return sf.getCurrentSession();
		    }

}
