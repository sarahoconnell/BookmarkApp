package ie.cit.adf.services;

import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.dao.BoardRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Secured({"ROLE_USER", "ROLE_ADMIN"})
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
	
	public  Collection<Board> findAllPublicByUserId(String userId, boolean ispublic) {
		return repo.findAllPublicByUserId(userId, ispublic);
	}

	public  Collection<Board> findAllPublic() {
		return repo.findAllPublic();
	}
	
	public Board create(Board board) {
		repo.create(board);
		return board;
	}

	public Board create(String name, String description, String userId, String img, boolean ispublic) {
		Board board = new Board();
		board.setName(name);
		board.setDescription(description);
		board.setUserId(userId);
		board.setImg(img);
		board.setIsPublic(ispublic);
		repo.create(board);
		return board;
	}

	@Transactional
	public Board update(String boardId, String name, String description, String img, boolean ispublic) {
		Board board = repo.findById(boardId);
		board.setName(name);
		board.setDescription(description);
		board.setImg(img);
		repo.update(board);
		board.setIsPublic(ispublic);
		return board;
		
	}

	@Transactional
	public Board update(Board board) {
		repo.update(board);
		return board;
		
	}

	@Override
	public void delete(Board board) {
		repo.delete(board);
	}
	


	@Override
	public void delete(String boardId) {
		Board board = repo.findById(boardId);
		repo.delete(board);
	}

}
