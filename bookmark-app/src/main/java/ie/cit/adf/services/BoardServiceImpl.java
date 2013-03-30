package ie.cit.adf.services;

import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.dao.BoardRepository;

import java.util.List;

public class BoardServiceImpl implements BoardService {
	private BoardRepository repo;

	public BoardServiceImpl(BoardRepository repo) {
		this.repo = repo;
	}

	public List<Board> getAll() {
		return repo.getAll();
	}
	

	public List<Board> getAll(String userId) {
		return repo.getAll(userId);
	}


	public Board create(String name, String description, String userId) {
		Board board = new Board();
		board.setName(name);
		board.setDescription(description);
		board.setUserId(userId);
		repo.add(board);
		return board;
	}


	public Board get(String boardId) {
		return repo.findById(boardId);
	}

	public Board update(String boardId, String name, String description) {
		Board board = repo.findById(boardId);
		board.setName(name);
		board.setDescription(description);
		repo.update(board);
		return board;
		
	}

	@Override
	public void delete(String boardId) {
		repo.delete(boardId);
	}

}
