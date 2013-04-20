package ie.cit.adf.rest;

import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.Boards;
import ie.cit.adf.domain.Link;
import ie.cit.adf.domain.Links;

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
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/system-test-context.xml")
public class RestClientTest  {

	 @Autowired
	 @Qualifier("restTemplate")
	 private RestTemplate restTemplate;
	 
	 String name;
	 String description;
	 String userId; 
	 String img;
	 String url;
	 String sampleBoardId; 
	 String sampleLinkId; 
	 
	 @org.junit.Before
	 public void setUp() throws Exception
	 { 
	     name = UUID.randomUUID().toString();
	     description = "Test Rest";
	     userId = ""; // GET FROM REST
	     img = "news.jpg";
	     url = "www.google.com";   

	     // use the content from setup-data.sql since the rest calls go through the main app
	     userId = "001";	   
	     sampleBoardId = "b01";
	     sampleLinkId = "l01";  
	 }


	 @org.junit.After
	 public void tearDown() throws Exception
	 {	     
	     // clean down
		 // nothing to do
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
	 //curl -X POST -i http://localhost:8080/bookmark-app/api/boards?name=name&description=description&userid=userid&img=img
	 // curl -X DELETE -i http://localhost:8080/bookmark-app/api/boards/{id}
	 public void createBoardTest() {
		 	 
		 Board board = createBoard(name, description, userId, img);
		 Assert.assertNotNull(board);
		 Assert.assertNotNull(board.getId());
		 Assert.assertTrue(board.getName() == name);
		 Assert.assertTrue(board.getDescription() == description);
		 Assert.assertTrue(board.getUserId() == userId);
		 Assert.assertTrue(board.getImg() == img);
		 
		 // clean up
		 deleteBoard(board.getId());

		 Board board2 = getBoard(board.getId());
		 Assert.assertNull(board2);
	 }
	 
		
	 @Test
	 //curl -X POST -i http://localhost:8080/bookmark-app/api/boards/{id}/links?name=name&description=description&url=url
	 // curl -X DELETE -i http://localhost:8080/bookmark-app/api/boards/links/{id}
	 public void createLinkForBoardTest() {
		 		 
		 Link link = createLink(sampleBoardId, name, description, url);
		 Assert.assertNotNull(link);
		 Assert.assertNotNull(link.getId());
		 Assert.assertTrue(link.getName() == name);
		 Assert.assertTrue(link.getDescription() == description);
		 Assert.assertTrue(link.getUrl() == url);
		  
		 //clean up
		 deleteLink(link.getId());
		 Link link2 = getLink(link.getId());
		 Assert.assertNull(link2);
	 }
	
			
	 
	
	 @Test 
	 // curl -X PUT -i http://localhost:8080/bookmark-app/api/boards/{id} -d
	 public void updateBoardTest() {

		 Board board = createBoard(name, description, userId, img);
		 String oldId = board.getId();
		 String oldName = board.getName();
		 String oldDesc = board.getDescription();
		 String oldImg = board.getImg();
		 	 
		 board.setName("Updated test");
		 board.setDescription("Updated test");
		 board.setImg("social.jpg");
		 
		 updateBoard(board);
	
		 Board updatedBoard = getBoard(oldId);
		 Assert.assertNotNull(updatedBoard);
		 // id should be the same
		 Assert.assertEquals(updatedBoard.getId(), oldId);
		 
		 // updated board vales should not be the same as the old
		 Assert.assertNotEquals(updatedBoard.getName(), oldName);
		 Assert.assertNotEquals(updatedBoard.getDescription(), oldDesc);
		 Assert.assertNotEquals(updatedBoard.getImg(), oldImg);
		 Assert.assertEquals(updatedBoard.getName(), board.getName());
		 Assert.assertEquals(updatedBoard.getDescription(), board.getDescription());
		 Assert.assertEquals(updatedBoard.getImg(), board.getImg());
	 }
	
	 @Test 
	 //curl -X PUT -i http://localhost:8080/bookmark-app/api/boards/links/{id} -d
	 public void updateLinkTest() {

		 Link link = createLink(sampleBoardId, name, description, url);
		 String oldId = link.getId();
		 String oldName = link.getName();
		 String oldDesc = link.getDescription();
		 String oldUrl = link.getUrl();
		 	 
		 link.setName("Updated test");
		 link.setDescription("Updated test");
		 link.setUrl("yahoo.co.uk");
		 
		 updateLink(link);
	
		 Link updatedLink = getLink(oldId);
		 Assert.assertNotNull(updatedLink);
		 // id should be the same
		 Assert.assertEquals(updatedLink.getId(), oldId);
		 
		 // updated Link vales should not be the same as the old
		 Assert.assertNotEquals(updatedLink.getName(), oldName);
		 Assert.assertNotEquals(updatedLink.getDescription(), oldDesc);
		 Assert.assertNotEquals(updatedLink.getUrl(), oldUrl);
		 Assert.assertEquals(updatedLink.getName(), link.getName());
		 Assert.assertEquals(updatedLink.getDescription(), link.getDescription());
		 Assert.assertEquals(updatedLink.getUrl(), link.getUrl());
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
	 
	 public Board createBoard(String name, String description, String userId, String img) {
		 MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		 parameters.add("name", name);
//		 parameters.add("description", description);
//		 parameters.add("userId", userId);
//		 parameters.add("img", img);
		 
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);      

		 HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
//		 
		 Board board = restTemplate.postForObject("http://localhost:8080/bookmark-app/api/boards", request, Board.class);		
		 return board;
	 }
	
	 public Link createLink(String boardId, String name, String description, String url) {
		 MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		 parameters.add("name", name);
		 parameters.add("description", description);
		 parameters.add("url", url);
		 Link link = restTemplate.postForObject("http://localhost:8080/bookmark-app/api/boards/"+boardId+"/links", parameters, Link.class);		
		 return link;
	 }
	 
	 public void deleteBoard(String boardId) {
		restTemplate.delete("http://localhost:8080/bookmark-app/api/boards/"+boardId, Board.class);
	 } 
	 
	 public void deleteLink(String linkId) {
		restTemplate.delete("http://localhost:8080/bookmark-app/api/links/"+linkId, Link.class);
	 } 
	
	 public void updateBoard(Board board) {
		 MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		 parameters.add("name", board.getName());
		 parameters.add("description", board.getDescription());
		 parameters.add("userId", board.getUserId());
		 parameters.add("img", board.getImg());
		 			 
		 restTemplate.put("http://localhost:8080/bookmark-app/api/boards/"+board.getId(), Board.class, parameters);	
	 }
	 
	
	 public void updateLink(Link link) {
		 MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		 parameters.add("name", link.getName());
		 parameters.add("description", link.getDescription());
		 parameters.add("url", link.getUrl());
		 			 
		 restTemplate.put("http://localhost:8080/bookmark-app/api/links/"+link.getId(), Link.class, parameters);	
	 }
}