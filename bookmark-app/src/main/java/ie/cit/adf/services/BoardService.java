package ie.cit.adf.services;

import ie.cit.adf.domain.Board;

import java.util.Collection;

public interface BoardService {

	// GET {id}
	Board findById(String id);
	
	// GET
	Collection<Board> findAll();
	
	// GET
	Collection<Board> findAllByUserId(String userId);

	// PUBLIC / PRIVATE lists
	Collection<Board> findAllPublicByUserId(String userId, boolean ispublic);
	Collection<Board> findAllPublic();
	Board findPublicById(String id);
	
	// POST
	Board create(String name, String description, String userId, String img, boolean ispublic);
	Board create(Board board);

	// PUT
	Board update(String id, String name, String description, String img, boolean ispublic);
	Board update(Board board);

	void delete(Board board);
	
	// DELETE {id}
	void delete(String id);

}
