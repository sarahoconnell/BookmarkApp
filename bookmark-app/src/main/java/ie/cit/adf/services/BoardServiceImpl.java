package ie.cit.adf.services;

import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.dao.BoardRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
    
	@Autowired
    @Qualifier("hibernateBoardRepository")
	BoardRepository repo;

	public Board findById(String boardId) {
		return repo.findById(boardId);
	}
	
	public Collection<Board> findAll() {
		return repo.findAll();
	}
	

	public  Collection<Board> findAllByUserId(String userId) {
		return repo.findAllByUserId(userId);
	}
	

	public Board create(String name, String description, String userId) {
		Board board = new Board();
		board.setName(name);
		board.setDescription(description);
		board.setUserId(userId);
		repo.create(board);
		return board;
	}

	public Board update(String boardId, String name, String description) {
		Board board = repo.findById(boardId);
		board.setName(name);
		board.setDescription(description);
		repo.update(board);
		return board;
		
	}

	@Override
	public void delete(Board board) {
		repo.delete(board);
	}

}
