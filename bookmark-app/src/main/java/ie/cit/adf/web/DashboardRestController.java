package ie.cit.adf.web;

import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.Boards;
import ie.cit.adf.domain.Link;
import ie.cit.adf.domain.Links;
import ie.cit.adf.services.BoardService;
import ie.cit.adf.services.LinkService;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

@Controller
@RequestMapping("/api")  
public class DashboardRestController extends BaseController {
	
	@Autowired
	private BoardService boardService;
	@Autowired  
	private LinkService linkService;
	

	// curl -X GET -i http://localhost:8080/bookmark-app/api/boards/
	@RequestMapping(value = "/boards", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Boards findAllBoards() {
		
			Collection<Board> boardsColl = boardService.findAll();
			if (boardsColl == null)
				throw new NotFoundException();
			Boards boards = new Boards();
			boards.setBoards(new ArrayList<Board>(boardsColl));
			return boards;		
	}
	

	// curl -X GET -i http://localhost:8080/bookmark-app/api/boards/{id}
	@RequestMapping(value = "/boards/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Board findBoardById(@PathVariable String id) {
		
			Board board = boardService.findById(id);
			if (board == null)
				throw new NotFoundException();
			return board;		
	}
	
	// curl -X POST -i http://localhost:8080/bookmark-app/api/boards/ -d
	// '{"name":"some name","description":"some text","img":"some.jpg"}'
	@RequestMapping(value = "/boards", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void createBoard(@RequestBody Board board,
							HttpServletRequest req,
							HttpServletResponse resp) {		
		
		Board newBoard = new Board(); // generate id
		newBoard.setDescription(board.getDescription());
		newBoard.setImg(board.getImg());
		newBoard.setName(board.getName());
		newBoard.setUserId(board.getUserId());
		boardService.create(newBoard);

		StringBuffer url = req.getRequestURL().append("/{id}");
		UriTemplate uriTemplate = new UriTemplate(url.toString());
		resp.addHeader("location", uriTemplate.expand(newBoard.getId()).toASCIIString());
	} 
	
	// curl -X DELETE -i http://localhost:8080/bookmark-app/api/boards/{id}
	@RequestMapping(value = "/boards/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void deleteBoard(@PathVariable String id) {		
		boardService.delete(id);
	} 

	// curl -X PUT -i http://localhost:8080/bookmark-app/api/boards/{id} -d
	// '{"name":"some name","description":"some text","img":"some.jpg"}'
	@RequestMapping(value = "/boards/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void updateBoard(@PathVariable String id, 
							@RequestBody Board board,
							HttpServletRequest req,
							HttpServletResponse resp) {		
		
		Board existing = boardService.findById(id);
		if (existing == null)
			throw new NotFoundException();
		existing.setDescription(board.getDescription());
		existing.setImg(board.getImg());
		existing.setName(board.getName());
		boardService.update(existing);

	} 
	
	// TODO DEFECT WITH BYTE[]
	// curl -X GET -i http://localhost:8080/bookmark-app/api/links/
	@RequestMapping(value = "/links", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Links findAllLinks() {
		
			Collection<Link> linksColl = linkService.findAll();
			if (linksColl == null)
				throw new NotFoundException();
			Links links = new Links();
			links.setLinks(new ArrayList<Link>(linksColl));
			return links;		
	}
	

	// curl -X GET -i http://localhost:8080/bookmark-app/api/boards/{id}/links
	@RequestMapping(value = "/boards/{id}/links", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Links findBoardLinksById(@PathVariable String id) {
		   
			Board board = boardService.findById(id);
			if (board == null)
				throw new NotFoundException();
			
			Collection<Link> linksColl = linkService.findAllByBoardId(board.getId());
			if (linksColl == null)
				throw new NotFoundException();
			Links links = new Links();
			links.setLinks(new ArrayList<Link>(linksColl));
			return links;		
	}
	

	// curl -X GET -i http://localhost:8080/bookmark-app/api/links/{id}
	@RequestMapping(value = "/links/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Link findLinkById(@PathVariable String id) {
		
			Link link = linkService.findById(id);
			if (link == null)
				throw new NotFoundException();
			return link;		
	}
	

	// curl -X POST -i http://localhost:8080/bookmark-app/api/boards/{id}/links?name=name&description=description&url=url
	@RequestMapping(value = "/boards/{id}/links", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void createLink(@PathVariable String id,
						   @RequestParam String name, 
						   @RequestParam String description, 
			               @RequestParam String url, 
						   HttpServletRequest req,
						   HttpServletResponse resp) {
		
		// TODO userid
		// TODO get thumbnail from URL
		Link link  = linkService.create(url, name, description, id, null);
								
		StringBuffer linkUrl = req.getRequestURL().append("/{id}");
		UriTemplate uriTemplate = new UriTemplate(linkUrl.toString());
		resp.addHeader("location", uriTemplate.expand(link.getId()).toASCIIString());
	}


	// curl -X DELETE -i http://localhost:8080/bookmark-app/api/boards/links/{id}
	@RequestMapping(value = "/links/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void deleteLink(@PathVariable String id) {		
		linkService.delete(id);
	} 

	// curl -X PUT -i http://localhost:8080/bookmark-app/api/boards/links/{id} -d
	// '{"name":"some name","description":"some text","url":"url"}'
	@RequestMapping(value = "/links/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void updateLink(@PathVariable String id, @RequestBody Link link) {		
		
		Link existing = linkService.findById(id);
		if (existing == null)
			throw new NotFoundException();
		existing.setDescription(link.getDescription());
		existing.setName(link.getName());
		existing.setUrl(link.getUrl());
		// TODO get image thumbnail from url (if different)
	} 
	
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
}

@ResponseStatus(HttpStatus.PROXY_AUTHENTICATION_REQUIRED)
class AuthenticationRequiredException extends RuntimeException{
	private static final long serialVersionUID = 2L;
}
