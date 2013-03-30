package ie.cit.adf.services;

import ie.cit.adf.domain.Board;

import java.util.List;

public interface BoardService {

	// GET
	List<Board> getAll();
	
	// GET
	List<Board> getAll(String userId);

	// POST
	Board create(String name, String description, String userId);

	// PUT
	Board update(String id, String name, String description);

	// GET {id}
	Board get(String id);

	// DELETE {id}
	void delete(String id);

}
