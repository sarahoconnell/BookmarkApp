package ie.cit.adf.integration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import ie.cit.adf.constants.Constants;
import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.Link;
import ie.cit.adf.services.BoardService;
import ie.cit.adf.services.LinkService;
import ie.cit.adf.services.UserService;
import ie.cit.adf.web.DashboardController;
import ie.cit.adf.web.UserController;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/system-test-context.xml")

public class DashboardIntegrationTest extends AuthenticationHelper{
	
	@Autowired
	private UserService userService;

	
	@Autowired 
	private DashboardController dashboardController;
	
	@Autowired 
	private UserController userController;
	
	@Autowired 
	private BoardService boardService;
	
	@Autowired
	private LinkService linkService;
	
	
	@Test
	public void testShowPublicBoards(){
		ModelMap model = new ExtendedModelMap();
		// no need to login
		
		String page = dashboardController.showPublicBoards(model);
		assertTrue(page.equals(Constants.publicBoardsPage));
		//check that the private/public Boards attribute is set 
		Collection<Board> publicBoards = (Collection<Board>)model.get(Constants.publicBoards);
		assertTrue(publicBoards != null);
		assertTrue(publicBoards.size()>0);
		for (Board board : publicBoards) 
			assertTrue(board.getIsPublic());
		
		Collection<Board> privateBoards = (Collection<Board>)model.get(Constants.privateBoards);
		assertTrue(privateBoards == null);
	}

	@Test
	public void testViewPublicBoard(){
		ModelMap model = new ExtendedModelMap();
		// no need to login
		
		// get a public board first
		String page = dashboardController.showPublicBoards(model);
		assertTrue(page.equals(Constants.publicBoardsPage));
		//check that the private/public Boards attribute is set 
		Collection<Board> publicBoards = (Collection<Board>)model.get("publicBoards");
		assertTrue(publicBoards != null);
		Board board = null;
		if(publicBoards.iterator().hasNext())
			board = publicBoards.iterator().next();
		
		//view the board links
		page = dashboardController.viewPublicBoard(board.getId(), model);
		assertTrue(page.equals(Constants.publicBoardPage));
		//check that the link/board attribute is set 
		Collection<Board> links = (Collection<Board>)model.get(Constants.links);
		assertTrue(links != null);
		assertTrue(links.size()>0);
		Board publicBoard = (Board)model.get(Constants.board);
		assertTrue(publicBoard != null);
		assertTrue(publicBoard.getId() == board.getId());
	}
	@Test
	public void testViewBoard(){
		ModelMap model = new ExtendedModelMap();
		// login
		login("user", "password");
		
		// get a private boardid
		String page = dashboardController.dashboard(model);
		Collection<Board> privateBoards = (Collection<Board>)model.get(Constants.privateBoards);
		assertTrue(privateBoards.size()>0);
		assertTrue(privateBoards != null);
		Board board = null;
		if(privateBoards.iterator().hasNext())
			board = privateBoards.iterator().next();
		
		page = dashboardController.viewBoard(board.getId(), model);
		assertTrue(page.equals(Constants.boardPage));
		//check that the link/board attribute is set 
		Collection<Board> links = (Collection<Board>)model.get(Constants.links);
		assertTrue(links != null);
		assertTrue(links.size()>0);
		Board publicBoard = (Board)model.get(Constants.board);
		assertTrue(publicBoard != null);
		assertTrue(publicBoard.getId() == board.getId());
	}
	@Test
	public void testViewDashboard(){
		ModelMap model = new ExtendedModelMap();
		//login and check that you are directed to dashboard.jsp 
		login("user", "password");
		String page = dashboardController.dashboard(model);
		assertTrue(page.equals(Constants.dashboardPage));
		//check that the private/public Boards attribute is set 
		Collection<Board> publicBoards = (Collection<Board>)model.get(Constants.publicBoards);
		assertTrue(publicBoards != null);
		Collection<Board> privateBoards = (Collection<Board>)model.get(Constants.privateBoards);
		assertTrue(privateBoards != null);
	}
	
	@Test
	public void testViewAdmin(){
		ModelMap model = new ExtendedModelMap();
		//login and check that you are directed to admin.jsp 
		login("admin", "password");
		String page = userController.showAdmin(model);
		assertTrue(page.equals(Constants.adminPage));
		//check that the private/public Boards attribute is set 
		Collection<Board> users = (Collection<Board>)model.get("users");
		assertTrue(users != null);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void testAdminAccessDenied(){
		login("user", "password");
		ModelMap model = new ExtendedModelMap();
		String page = userController.showAdmin(model);
	}
	@Test
	public void testAdminAccess(){
		login("admin", "password");
		ModelMap model = new ExtendedModelMap();
		String page = userController.showAdmin(model);
		assertTrue(page.equals(Constants.adminPage));
	}

	@Test 
	public void testRemoveUser(){
		//create a user first 
		Model model = new ExtendedModelMap();
		userController.create("temp", "temp", "temp", "twitter", model);
		//find the user 
		String uid = userService.findByName("temp").getId();
		login("admin", "password");
		ModelMap modelMap = new ExtendedModelMap();
		userController.removeUser(uid, modelMap);
		
		assertTrue(userService.findById(uid) == null);
	}
	
	

	@Test 
	public void testUserEnablement(){
		//create a user first 
		Model model = new ExtendedModelMap();
		userController.create("temp", "temp", "temp", "twitter", model);
		//find the user 
		String uid = userService.findByName("temp").getId();
		login("admin", "password");
		ModelMap modelMap = new ExtendedModelMap();
		userController.toggleUserEnable(uid,  false,  modelMap);
		assertFalse(userService.findById(uid).getEnabled());
		//re-enable the user 
		userController.toggleUserEnable(uid,  true,  modelMap);
		assertTrue(userService.findById(uid).getEnabled());
		//delete the user for cleanup 
		userController.removeUser(uid, modelMap);
	}
	
	
	@Test 
	public void testBoardOperations(){
		login("user", "password");
		Model model = new ExtendedModelMap();
		
		dashboardController.createBoard("", "New Board", "DEscription", "icon-star", false, model);
		//ensure the board exists for this user
		Collection<Board> boards = boardService.findAllByUserId(userService.findByName("user").getId());
		boolean foundBoard = false;
		String boardId = null;
		for(Board b : boards){
			if(b.getName().equalsIgnoreCase("New Board")){
				foundBoard = true;
				boardId = b.getId();
			}
		}
		assertTrue(foundBoard);
		
		//test updating the board 
		dashboardController.createBoard(boardId, "A Better Name", "New Description", "icon-star",false,   model);
		//make sure the board settings were saved 
		Board board = boardService.findById(boardId);
		assertTrue(board.getName().equals("A Better Name"));
		
		ModelMap modelMap = new ExtendedModelMap();
		
		//delete the board 
		dashboardController.deleteBoard(boardId, modelMap);
		board = boardService.findById(boardId);
		assertTrue(board == null);
	}
	
	
	@Test 
	public void testLinkOperations(){
		login("user", "password");

		Model model = new ExtendedModelMap();
		
		
		//create a board for this 
		dashboardController.createBoard("", "LinkBoard", "Tests", "icon-star",false,  model);
		//Get one of the boards 
		Collection<Board> boards = boardService.findAllByUserId(userService.findByName("user").getId());
		Board board = boards.iterator().next();
		int numLinks = linkService.findAllByBoardId(board.getId()).size();

		//add a link 
		dashboardController.createLink("", "http://google.com","Google", "Google Search", board.getId(), "http://google.com", model);
		
		//check that link is created
		int newNumLinks = linkService.findAllByBoardId(board.getId()).size();
		assertTrue(newNumLinks == (numLinks +1));
		
		//get the link id 
		Collection<Link> links  = linkService.findAllByBoardId(board.getId());
		boolean foundLink = false;
		String linkId = null;
		for(Link l : links){
			if(l.getUrl().equals("http://google.com")){
			   foundLink = true;
			   linkId = l.getId();
			}
		}
		//update the link 
		dashboardController.createLink(linkId, "http://apple.com","Apple", "Apple", board.getId(), "http://apple.com", model);
		assertTrue(linkService.findById(linkId).getName().equals("Apple"));
		
		//and now delete it 
		ModelMap modelMap = new ExtendedModelMap();
		dashboardController.deleteLink(linkId, modelMap);
		//make sure it's gone 
		assertTrue(linkService.findById(linkId) == null);
	}	
	
}
