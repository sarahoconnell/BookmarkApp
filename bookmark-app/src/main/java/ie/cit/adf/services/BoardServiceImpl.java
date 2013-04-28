package ie.cit.adf.services;

import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.dao.BoardRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
    
	@Autowired
    @Qualifier("hibernateBoardRepository")
	BoardRepository repo;

	// not secured
	public  Collection<Board> findAllPublic() {
		return repo.findAllPublic();
	}

	// not secured
	public Board findPublicById(String boardId) {
		return repo.findPublicById(boardId);
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public Board findById(String boardId) {
		return repo.findById(boardId);
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public Collection<Board> findAll() {
		return repo.findAll();
	}	

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public  Collection<Board> findAllByUserId(String userId) {
		return repo.findAllByUserId(userId);
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public  Collection<Board> findAllPublicByUserId(String userId, boolean ispublic) {
		return repo.findAllPublicByUserId(userId, ispublic);
	}


	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public Board create(Board board) {
		repo.create(board);
		return board;
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
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

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
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
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public Board update(Board board) {
		repo.update(board);
		return board;
		
	}

	@Override
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public void delete(Board board) {
		repo.delete(board);
	}
	
	@Override
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public void delete(String boardId) {
		Board board = repo.findById(boardId);
		repo.delete(board);
	}

}
