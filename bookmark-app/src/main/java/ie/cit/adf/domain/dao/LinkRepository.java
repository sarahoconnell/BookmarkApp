package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Link;

import java.util.Collection;

public interface LinkRepository {

    Link get(String id);

    void create(Link link);

    void update(Link link);
   
    void delete(Link link);

    Link findById(String id);
    
    Collection<Link> findAllByBoardId(String boardId);
    Collection<Link> findAll();

}
