package ie.cit.adf.web;

import ie.cit.adf.constants.Constants;
import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.Link;
import ie.cit.adf.services.BoardService;
import ie.cit.adf.services.LinkService;
import ie.cit.adf.services.UserService;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class DashboardController extends BaseController {  
		
	@Autowired 
	private RestTemplate restTemplate;
	@Autowired
	private BoardService boardService;
	@Autowired  
	private LinkService linkService;
	@Autowired
	private UserService userService;
	
	// NOT SECURED - VISIBLE TO ALL	
	@RequestMapping(value=Constants.showPublicBoardsMapping, method = RequestMethod.GET)
	public String showPublicBoards(ModelMap model) {

		Collection<Board> allPublicBoards = boardService.findAllPublic();
		model.addAttribute(Constants.publicBoards, allPublicBoards);
		return Constants.publicBoardsPage;			 
	}

	// NOT SECURED - VISIBLE TO ALL	
	@RequestMapping(value=Constants.viewPublicBoardMapping, method = RequestMethod.GET)
	public String viewPublicBoard(@RequestParam String boardid, ModelMap model) {

		Board board = boardService.findById(boardid);
		Collection<Link> allLinks = linkService.findAllByBoardId(boardid);
		model.addAttribute(Constants.links, allLinks);
		model.addAttribute(Constants.board, board);
		return Constants.publicBoardPage;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value=Constants.dashboardMapping, method = RequestMethod.GET)
	public String dashboard(ModelMap model) {

		if (loggedIn()) 
		{	
			Collection<Board> allPublicBoards = boardService.findAllPublicByUserId(loggedInUser.getId(), true);
			model.addAttribute(Constants.publicBoards, allPublicBoards);
			Collection<Board> allPrivateBoards = boardService.findAllPublicByUserId(loggedInUser.getId(), false);
			model.addAttribute(Constants.privateBoards, allPrivateBoards);

			// don't allow the sample user page to be editable
			if(!getLoggedInUserId().equals("001"))
				model.addAttribute(Constants.editable, true);
			else
				model.addAttribute(Constants.editable, false);
		
			return Constants.dashboardPage;
		}
		else
		    return Constants.indexMapping;			
 
	}

 
	@Secured("ROLE_USER")
	@RequestMapping(value=Constants.viewBoardMapping, method = RequestMethod.GET)
	public String viewBoard(@RequestParam String boardid, ModelMap model) {

		if (loggedIn()) // ROLE?
		{	
			Board board = boardService.findById(boardid);
			Collection<Link> allLinks = linkService.findAllByBoardId(boardid);
			model.addAttribute(Constants.links, allLinks);
			model.addAttribute(Constants.board, board);			

			// don't allow the sample user page to be editable
			if(!getLoggedInUserId().equals("001"))
				model.addAttribute(Constants.editable, true);
			else
				model.addAttribute(Constants.editable, false);		
			
			return Constants.boardPage;
		}
		else
		    return Constants.indexMapping;		
	}

	@Secured("ROLE_USER")
	@RequestMapping(value=Constants.deleteBoardMapping, method = RequestMethod.POST)
	public String deleteBoard(@RequestParam String boardid, ModelMap model) {

		if (loggedIn()) // ROLE?
		{	
			Board board = boardService.findById(boardid);
			boardService.delete(board);
			return Constants.redirect+Constants.dashboardMapping;
		}
		else
			return Constants.redirect+Constants.indexMapping;
 	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value=Constants.deleteLinkMapping, method = RequestMethod.POST)
	public String deleteLink(@RequestParam String linkid, ModelMap model) {

		if (loggedIn()) // ROLE?
		{	
			Link link = linkService.findById(linkid);
			linkService.delete(link);
			return Constants.redirect+Constants.viewBoardMapping+"?boardid="+link.getBoardId();
		}
		else
			return Constants.redirect+Constants.indexMapping;		
 
	}
	

	@Secured("ROLE_USER")
	@RequestMapping(value=Constants.createLinkMapping, method = RequestMethod.POST)
	public String createLink(@RequestParam String id, @RequestParam String url, @RequestParam String name, @RequestParam String description, @RequestParam String boardId, @RequestParam String gotolink, Model model) {
	
		if (loggedIn()) // ROLE?
		{			
			byte[] thumbnailData = null; 
			
			if(!id.isEmpty()){
				Link link = linkService.findById(id);
				
				if(!link.getUrl().equals(url)){
					thumbnailData = generateSnapshot(boardId, url, name);
					link.setImage(thumbnailData);
					//defer the update so we only have one call going through
				}
				linkService.update(id, url, name, description, boardId, link.getImage());
				
			}
			else{ 
				linkService.create(url, name, description, boardId, generateSnapshot(boardId, url, name));
				
			}
			
			if(gotolink.equalsIgnoreCase(Constants.dashboard))
				return Constants.redirect+Constants.dashboardMapping;
			else
				return Constants.redirect+Constants.viewBoardMapping+"?boardid="+boardId;
		}
		else
			return Constants.redirect+Constants.indexMapping;
	}

	@Secured("ROLE_USER")
	@RequestMapping(value=Constants.createBoardMapping, method = RequestMethod.POST)
	public String createBoard(@RequestParam String id, @RequestParam String name, @RequestParam String description, @RequestParam String img,  @RequestParam boolean ispublic, Model model) {

		if (loggedIn()) // ROLE?
		{
			// validation
			if(name.isEmpty())
			{
				model.addAttribute(Constants.error, Constants.nameMandatory);
				return Constants.redirect+Constants.dashboardMapping;
			}
			
			if(!id.isEmpty()) 		
				boardService.update(id, name, description, img, ispublic);
			else 
				boardService.create(name, description, loggedInUser.getId(), img, ispublic);	

			Collection<Board> allBoards = boardService.findAllByUserId(loggedInUser.getId());
			model.addAttribute(Constants.boards, allBoards);	
			return Constants.redirect+Constants.dashboardMapping;
	   }
	   else
			return Constants.redirect+Constants.indexMapping;
	}
	

	/**
	 * Get a snapshot using a RESTful service.
	 * 
	 * @param boardId
	 * @param url
	 * @param name
	 * @return
	 */
	private byte[] generateSnapshot(String boardId, String url, String name){
			//TODO: Refactor out to a utility class?
			//TODO: Buy credits so we can generate bigger images :) 
		
			//get the image for this URL using the W3Snapshot service
			String apikey = "e8c2173d19e93d234627817c039dea6d";
			//http://images.w3snapshot.com/?size=[size]&key=[key]&url=[url]&format=[format]&quality=[quality]			 
			String service = "http://images.w3snapshot.com/?size=S&key="+apikey+"&url="+url;//"&format=[format]&quality=[quality]";
			System.out.println(service);
			BufferedImage image = restTemplate.getForObject( service, BufferedImage.class);
			if(image != null){
				try {
					ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
					ImageIO.write(image, "jpg", byteStream);				    
					byteStream.flush();
					return byteStream.toByteArray();
				} catch (IOException e) {
				    e.printStackTrace();
				}
			}
			return null;
	}	
}
