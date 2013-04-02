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
	
	// POST
	Board create(String name, String description, String userId);

	// PUT
	Board update(String id, String name, String description);

	// DELETE {id}
	void delete(Board board);

}
