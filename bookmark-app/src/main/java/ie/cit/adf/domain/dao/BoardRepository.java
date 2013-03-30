package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Board;

import java.util.List;

public interface BoardRepository {

	Board findById(String id);

	List<Board> getAll();
	
	// by user
	List<Board> getAll(String id);

	void add(Board board);

	void delete(String id);

	void update(Board board);

}
