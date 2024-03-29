package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Board;

import java.util.Collection;

public interface BoardRepository {

    Board get(String id);

    void create(Board board);

    void update(Board board);
   
    void delete(Board board);

    Board findById(String id);
    Board findPublicById(String id);
    Collection<Board> findAll();
    Collection<Board> findAllByUserId(String userId);
    Collection<Board> findAllPublicByUserId(String userId, boolean ispublic);
    Collection<Board> findAllPublic();

}
