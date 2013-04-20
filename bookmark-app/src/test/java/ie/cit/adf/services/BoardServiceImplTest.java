package ie.cit.adf.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.dao.BoardRepository;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BoardServiceImplTest {

	private BoardServiceImpl boardService;
	private BoardRepository boardRepository;

	@Before 
	public void setup() {
		boardRepository = Mockito.mock(BoardRepository.class);
		boardService = new BoardServiceImpl();
		boardService.repo = boardRepository;
	}
	
	@Test
	public void testCreateBoard(){
		Board board = boardService.create("My Board", "Board Description", "userid999", "fav.png",false);
		String id = board.getId();
		Mockito.verify(boardRepository).create(board);
		
		assertThat(board.getName(), equalTo("My Board"));
		assertThat(board.getDescription(), equalTo("Board Description"));
		assertThat(board.getId(), equalTo(id));
		assertThat(board.getUserId(), equalTo("userid999"));
	}
	
	
	
	@Test
	public void testFindAll() {  
		Board board1 = boardService.create("Sarah's Board", "A board for Sarah", "sarah", "fav.png",false);
		Board board2 = boardService.create("Sarah's SecondBoard", "A second board for Sarah", "sarah", "fav.png",false);
		Board board3 = boardService.create("John's Board", "A board for John", "john", "fav.png",false);
		Board notIncluded = boardService.create("Sally's Board", "", "", "fav.png",false);
		
		ArrayList<Board> boards = new ArrayList<Board>();
		boards.add(board1);
		boards.add(board2);
		boards.add(board3);
		Mockito.when(boardRepository.findAll()).thenReturn(boards);

		int numberOfBoards = boardService.findAll().size();
		assertEquals(numberOfBoards, 3);

		assertEquals(boardService.findAll().contains(board1), true);
		assertEquals(boardService.findAll().contains(board2), true);
		assertEquals(boardService.findAll().contains(board3), true);
		assertEquals(boardService.findAll().contains(notIncluded),false);
		
		//ensure that the findAll method was called 5 times
		Mockito.verify(boardRepository, Mockito.times(5)).findAll();
	}
	

	@Test
	public void testFindByUserId() {  
		Board board1 = boardService.create("Sarah's Board", "A board for Sarah", "sarah", "fav.png",false);
		Board board2 = boardService.create("Sarah's SecondBoard", "A second board for Sarah", "sarah", "fav.png",false);
		Board board3 = boardService.create("John's Board", "A board for John", "john", "fav.png",false);
		Board notIncluded = boardService.create("Sally's Board", "", "sally", "fav.png",false);
		
		ArrayList<Board> sarahsBoards = new ArrayList<Board>();
		sarahsBoards.add(board1);
		sarahsBoards.add(board2);
		
		ArrayList<Board> johnsBoards = new ArrayList<Board>();
		johnsBoards.add(board3);
		
		Mockito.when(boardRepository.findAllByUserId(board1.getUserId())).thenReturn(sarahsBoards);
		Mockito.when(boardRepository.findAllByUserId(board3.getUserId())).thenReturn(johnsBoards);
		Mockito.when(boardRepository.findById(notIncluded.getId())).thenReturn(null);
		
		assertEquals(boardService.findAllByUserId(board1.getUserId()), sarahsBoards);
		assertEquals(boardService.findAllByUserId(board3.getUserId()), johnsBoards);
		
		assertThat(boardService.findAllByUserId(notIncluded.getUserId()).size(), equalTo(0));
		assertThat(boardService.findAllByUserId("random").size(), equalTo(0));
		
	
		//ensure that the find method in userRepostory was called the correct amount of times
		Mockito.verify(boardRepository, Mockito.times(1)).findAllByUserId(board1.getUserId());
		Mockito.verify(boardRepository, Mockito.times(1)).findAllByUserId(board3.getUserId());
		Mockito.verify(boardRepository, Mockito.times(1)).findAllByUserId(notIncluded.getUserId());
		Mockito.verify(boardRepository, Mockito.times(1)).findAllByUserId("random");

	
	}
	
	@Test
	public void testFindById() {  
		Board board1 = boardService.create("Sarah's Board", "A board for Sarah", "sarah", "fav.png",false);
		Board board2 = boardService.create("Sarah's SecondBoard", "A second board for Sarah", "sarah", "fav.png",false);
		Board board3 = boardService.create("John's Board", "A board for John", "john", "fav.png",false);
		Board notIncluded = boardService.create("Sally's Board", "", "sally", "fav.png",false);
		
		ArrayList<Board> sarahsBoards = new ArrayList<Board>();
		sarahsBoards.add(board1);
		sarahsBoards.add(board2);
		
		ArrayList<Board> johnsBoards = new ArrayList<Board>();
		johnsBoards.add(board3);
		
		
		Mockito.when(boardService.findById(board1.getId())).thenReturn(board1);
		Mockito.when(boardService.findById(board2.getId())).thenReturn(board2);
		Mockito.when(boardService.findById(board3.getId())).thenReturn(board3);
		Mockito.when(boardService.findById(notIncluded.getId())).thenReturn(null);
		
		assertEquals(boardService.findById(board1.getId()), board1);
		assertEquals(boardService.findById(board2.getId()), board2);
		assertEquals(boardService.findById(board3.getId()), board3);
		assertEquals(boardService.findById(notIncluded.getId()), null);
		assertEquals(boardService.findById("random"), null);
	
		//ensure that the find method in userRepostory was called the correct amount of times
		Mockito.verify(boardRepository, Mockito.times(1)).findById(board1.getId());
		Mockito.verify(boardRepository, Mockito.times(1)).findById(board2.getId());
		Mockito.verify(boardRepository, Mockito.times(1)).findById(board3.getId());
		Mockito.verify(boardRepository, Mockito.times(1)).findById(notIncluded.getId());
		Mockito.verify(boardRepository, Mockito.times(1)).findById("random");

	
	}

	@Test
	public void testUpdate() {  
		Board board1 = boardService.create("Sarah's Board", "A board for Sarah", "sarah", "fav.png",false);
		Board board2 = boardService.create("Sarah's SecondBoard", "A second board for Sarah", "sarah", "fav.png",false);
		
		Mockito.when(boardRepository.findById(board1.getId())).thenReturn(board1);
		Mockito.when(boardRepository.findById(board2.getId())).thenReturn(board2);
		
		
		board1.setName("New Name");
		boardService.update(board1.getId(), board1.getName(), board1.getDescription(), "fav.png",false);
		Mockito.verify(boardRepository, Mockito.times(1)).update(board1);
		assertEquals(board1.getName().equals("New Name"), true);
		
		board2.setDescription("New Description");
		boardService.update(board2.getId(), board2.getName(), board2.getDescription(), "fav.png",false);
		Mockito.verify(boardRepository, Mockito.times(1)).update(board2);
		assertEquals(board2.getDescription().equals("New Description"), true);
		
		
		
	}


	@Test
	public void testDelete() {  
		Board board1 = boardService.create("Sarah's Board", "A board for Sarah", "sarah", "fav.png",false);
		boardService.delete(board1);
		Mockito.verify(boardRepository).delete(board1);        
        
	}
	
}
