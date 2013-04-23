package ie.cit.adf.rest;

import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.Boards;
import ie.cit.adf.domain.Link;
import ie.cit.adf.domain.Links;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/system-test-context.xml")
public class RestClientTest  {

	 @Autowired
	 @Qualifier("restTemplate")
	 private RestTemplate restTemplate;
	 
	 String name = "Test Rest";
	 String description = "Test Rest";
	 String userId = "001"; 
	 String img = "icon-star";
	 String url = "www.google.com";     
	 String sampleBoardId = "b01";  
	 String sampleLinkId = "l02"; 
	 
	 @org.junit.Before
	 public void setUp() throws Exception
	 { 	     
	 }

	 @org.junit.After
	 public void tearDown() throws Exception
	 {	     
	     // clean down
		 // nothing to do
	 }
	 
	 @Test(expected = HttpClientErrorException.class)
	 public void getNotExistingBoardTest() {	
		 getBoard("INVALID");	 
	 }

	 @Test(expected = HttpClientErrorException.class)
	 public void getNotExistingLinkTest() {	
		 getLink("INVALID");	 
	 }

	 @Test(expected = HttpClientErrorException.class)
	 public void updateNotExistingBoardTest() {	
		 updateBoard(new Board());	 
	 }

	 @Test(expected = HttpClientErrorException.class)
	 public void updateNotExistingLinkTest() {	
		 updateLink(new Link());	 
	 }
	 

	 @Test(expected = HttpServerErrorException.class)
	 public void deleteNotExistingBoardTest() {	
		 deleteBoard(new Board().getId());	 
	 }

	 @Test(expected = HttpServerErrorException.class)
	 public void deleteNotExistingLinkTest() {	
		 deleteLink(new Link().getId());	 
	 }
	 
	 @Test 
	 //curl -X GET -i http://localhost:8080/bookmark-app/api/boards/
	 public void getAllBoardsTest() {
		 List<Board> boards = getAllBoards();
		 
		 Assert.assertNotNull(boards);
		 Assert.assertTrue(boards.size() > 0);
	 }
	 
	 @Test
	 //curl -X GET -i http://localhost:8080/bookmark-app/api/links/
	 public void getAllLinksTest() {
		 List<Link> links = getAllLinks();
		 
		 Assert.assertNotNull(links);
		 Assert.assertTrue(links.size() > 0);
	 } 
	
	 @Test
	 // curl -X GET -i http://localhost:8080/bookmark-app/api/boards/{id}
	 public void getBoardTest() {	
		 Board board = getBoard(sampleBoardId);
		 
		 Assert.assertNotNull(board);
		 Assert.assertTrue(board.getId().equals(sampleBoardId));		 
	 }
	 	
	 @Test
	 // curl -X GET -i http://localhost:8080/bookmark-app/api/links/{id}
	 public void getLinkTest() {
		 Link link = getLink(sampleLinkId);
		 
		 Assert.assertNotNull(link);
		 Assert.assertTrue(link.getId().equals(sampleLinkId));		 
	 }
	 
	 @Test
	 //curl -X GET -i http://localhost:8080/bookmark-app/api/boards/{id}/links
	 public void getAllLinksForBoardTest() {		 		 
		 List<Link> links = getAllLinksForBoard(sampleBoardId);	 
		 
		 Assert.assertNotNull(links);
		 Assert.assertTrue(links.size() > 0);
	 }
	 
	
	 @Test
	 // curl -X POST -i http://localhost:8080/bookmark-app/api/boards?name=name&description=description&userid=userid&img=img
	 // curl -X DELETE -i http://localhost:8080/bookmark-app/api/boards/{id}
	 // {"name":"NEW BOARD","description":"My Favourite Websites","userId":"001","img":"fav.png", "isPublic":true}
	 public void createBoardTest() {

		Board newBoard = new Board();
		newBoard.setDescription(description);
		newBoard.setImg(img);
		newBoard.setName(name);
		newBoard.setUserId(userId);
		newBoard.setIsPublic(true);
		
		URI boardLink = createBoard(newBoard);
		Assert.assertNotNull(boardLink);
		
		// test the location returned
		newBoard = restTemplate.getForObject(boardLink, Board.class);
		
		Assert.assertNotNull(newBoard);
		Assert.assertNotNull(newBoard.getId());
		Assert.assertTrue(newBoard.getName().equals(name));
		Assert.assertTrue(newBoard.getDescription().equals(description));
		Assert.assertTrue(newBoard.getUserId().equals(userId));
		Assert.assertTrue(newBoard.getImg().equals(img));
		 
		// clean up
		deleteBoard(newBoard.getId());
	 }
	 
		
	 @Test
	 // curl -X POST -i http://localhost:8080/bookmark-app/api/boards/{id}/links?name=name&description=description&url=url
	 // curl -X DELETE -i http://localhost:8080/bookmark-app/api/boards/links/{id}
	 public void createLinkForBoardTest() {
		 		 
		 // create a board
		 Board testBoard = new Board(); 
		 testBoard.setDescription(description);
		 testBoard.setImg(img);
		 testBoard.setName(name);
		 testBoard.setUserId(userId);
		 testBoard.setIsPublic(true);	
		 
		 URI boardLink = createBoard(testBoard);
		 Assert.assertNotNull(boardLink);
		 testBoard = restTemplate.getForObject(boardLink, Board.class); // get the right ID
		 
		 //create a link
		 Link newLink = new Link(); // generate id
		 newLink.setDescription(description);
		 newLink.setName(name);
		 newLink.setUrl(url);			
			
		 URI linkLocation = createLink(testBoard.getId(), newLink);
		 Assert.assertNotNull(linkLocation);
		
		 // test the location returned
		 newLink = restTemplate.getForObject(linkLocation, Link.class);
			
		 Assert.assertNotNull(newLink);
		 Assert.assertNotNull(newLink.getId());
		 Assert.assertTrue(newLink.getName().equals(name));
		 Assert.assertTrue(newLink.getDescription().equals(description));
		 Assert.assertTrue(newLink.getUrl().equals(url));
		  
		 //clean up
		 deleteBoard(testBoard.getId());			
		 deleteLink(newLink.getId());
	 }
	
			
	 
	
	 @Test 
	 // curl -X PUT -i http://localhost:8080/bookmark-app/api/boards/{id} -d
	 public void updateBoardTest() {

		 Board testBoard = new Board(); // generate id
		 testBoard.setDescription(description);
		 testBoard.setImg(img);
		 testBoard.setName(name);
		 testBoard.setUserId(userId);
		 testBoard.setIsPublic(true);			
			
		 URI boardLink = createBoard(testBoard);
		 Assert.assertNotNull(boardLink);

		 // test the location returned
		 testBoard = restTemplate.getForObject(boardLink, Board.class);

		 // save original values to test after update
		 String oldId = testBoard.getId();
		 String oldName = testBoard.getName();
		 String oldDesc = testBoard.getDescription();
		 String oldImg = testBoard.getImg();
		 	 
		 testBoard.setName("Updated test");
		 testBoard.setDescription("Updated test");
		 testBoard.setImg("icon-new");
		 
		 updateBoard(testBoard);
	
		 testBoard = getBoard(oldId);
		 Assert.assertNotNull(testBoard);
		 // id should be the same
		 Assert.assertEquals(testBoard.getId(), oldId);
		 
		 // updated board vales should not be the same as the old
		 Assert.assertNotEquals(testBoard.getName(), oldName);
		 Assert.assertNotEquals(testBoard.getDescription(), oldDesc);
		 Assert.assertNotEquals(testBoard.getImg(), oldImg);
		 Assert.assertEquals(testBoard.getName(), "Updated test");
		 Assert.assertEquals(testBoard.getDescription(), "Updated test");
		 Assert.assertEquals(testBoard.getImg(), "icon-new");
		 
		 //clean up
		 deleteBoard(testBoard.getId());
	 }
	
	 @Test 
	 //curl -X PUT -i http://localhost:8080/bookmark-app/api/boards/links/{id} -d
	 public void updateLinkTest() {

		 // create a board
		 Board testBoard = new Board(); // generate id
		 testBoard.setDescription(description);
		 testBoard.setImg(img);
		 testBoard.setName(name);
		 testBoard.setUserId(userId);
		 testBoard.setIsPublic(true);			
			
		 URI boardLink = createBoard(testBoard);
		 Assert.assertNotNull(boardLink);

		 // test the location returned
		 testBoard = restTemplate.getForObject(boardLink, Board.class);
		 
		 //create a link
		 Link testLink = new Link(); // generate id
		 testLink.setDescription(description);
		 testLink.setName(name);
		 testLink.setUrl(url);			
			
		 URI linkLocation = createLink(testBoard.getId(), testLink);
		 Assert.assertNotNull(linkLocation);

		 // test the location returned
		 testLink = restTemplate.getForObject(linkLocation, Link.class);
		 
		 // save original values to test after update
		 String oldId = testLink.getId();
		 String oldName = testLink.getName();
		 String oldDesc = testLink.getDescription();
		 String oldUrl = testLink.getUrl();
		 	 
		 testLink.setName("Updated test");
		 testLink.setDescription("Updated test");
		 testLink.setUrl("yahoo.co.uk");
		 
		 updateLink(testLink);
	
		 testLink = getLink(oldId);
		 Assert.assertNotNull(testLink);
		 // id should be the same
		 Assert.assertEquals(testLink.getId(), oldId);
		 
		 // updated Link vales should not be the same as the old
		 Assert.assertNotEquals(testLink.getName(), oldName);
		 Assert.assertNotEquals(testLink.getDescription(), oldDesc);
		 Assert.assertNotEquals(testLink.getUrl(), oldUrl);
		 Assert.assertEquals(testLink.getName(), testLink.getName());
		 Assert.assertEquals(testLink.getDescription(), testLink.getDescription());
		 Assert.assertEquals(testLink.getUrl(), testLink.getUrl());
		 
		 //clean up
		 deleteBoard(testBoard.getId());
		 deleteLink(testLink.getId());
	 }
	
	 
	 // Rest Calls 
	 public List<Board> getAllBoards() {
		 Boards boardsList = restTemplate.getForObject("http://localhost:8080/bookmark-app/api/boards", Boards.class);
		 List<Board> boards = boardsList.getBoards();
		 return boards;
	 }
	 
	
	 public List<Link> getAllLinks() {
		 Links linkList = restTemplate.getForObject("http://localhost:8080/bookmark-app/api/links", Links.class);
		 List<Link> links = linkList.getLinks();
		 return links;
	 }
	 
	 public Board getBoard(String boardId) {
		 Board board = restTemplate.getForObject("http://localhost:8080/bookmark-app/api/boards/"+boardId, Board.class);
		 return board;
	 } 
	
	 public Link getLink(String linkId) {
		 Link link = restTemplate.getForObject("http://localhost:8080/bookmark-app/api/links/"+linkId, Link.class);
		 return link;
	 } 
	
	 public List<Link> getAllLinksForBoard(String boardId) {
		 Links linksLink = restTemplate.getForObject("http://localhost:8080/bookmark-app/api/boards/"+boardId+"/links", Links.class);
		 List<Link> links = linksLink.getLinks();
		 return links;
	 }
	 
	 public URI createBoard(Board board) {
		URI boardLink = restTemplate.postForLocation("http://localhost:8080/bookmark-app/api/boards", board);
		return boardLink;
	 }
	
	 public URI createLink(String boardId, Link link) {
		 URI linkLink = restTemplate.postForLocation("http://localhost:8080/bookmark-app/api/boards/"+boardId+"/links", link);
		 return linkLink;
	 }
	 
	 public void deleteBoard(String boardId) {
		restTemplate.delete("http://localhost:8080/bookmark-app/api/boards/"+boardId, Board.class);
	 } 
	 
	 public void deleteLink(String linkId) {
		restTemplate.delete("http://localhost:8080/bookmark-app/api/links/"+linkId, Link.class);
	 } 
	
	 public void updateBoard(Board board) {
		restTemplate.put("http://localhost:8080/bookmark-app/api/boards/"+board.getId(), board);
	 }	 
	
	 public void updateLink(Link link) {
		 restTemplate.put("http://localhost:8080/bookmark-app/api/links/"+link.getId(), link);	
	 }
}