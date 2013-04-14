package ie.cit.adf.web;

import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.Link;
import ie.cit.adf.domain.User;
import ie.cit.adf.services.BoardService;
import ie.cit.adf.services.LinkService;
import ie.cit.adf.services.UserService;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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
	
	@RequestMapping(value="/dashboard")
	public String dashboard(ModelMap model) {

		if (loggedIn()) // ROLE?
		{	
			Collection<Board> allBoards = boardService.findAllByUserId(loggedInUser.getId());
			model.addAttribute("boards", allBoards);
		
			return "dashboard.jsp";
		}
		else
		    return "index.jsp";			
 
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public String showAdmin(ModelMap model) {
 
		if(loggedIn()){
			Collection<User> allUsers = userService.findAll();
			model.addAttribute("users", allUsers);
			model.addAttribute("currentUser", loggedInUser);
		}
		return "admin.jsp";
	}
	
	


	@RequestMapping(value="removeUser")
	@Secured("ROLE_ADMIN")
	public String removeUser(@RequestParam String userId, ModelMap model) {

		System.out.println("About to remove user" + userId);
		if (loggedIn()) // ROLE?
		{	
			User user = userService.findById(userId);
		    userService.delete(user);
			return "redirect:admin";
		}
		else
		    return "redirect:index.jsp";			
 
	}
	
	@RequestMapping(value="toggleUserEnable")
	@Secured("ROLE_ADMIN")
	public String toggleUserEnable(@RequestParam String userId, @RequestParam boolean enabled, ModelMap model) {

		System.out.println("About to remove user" + userId);
		if (loggedIn()) // ROLE?
		{	
			User user = userService.findById(userId);
		    user.setEnabled(enabled);
		    userService.update(user);
			return "redirect:admin";
		}
		else
		    return "redirect:index.jsp";			
 
	}
 
	
	@RequestMapping(value="viewBoard")
	public String viewBoard(@RequestParam String boardid, ModelMap model) {

		if (loggedIn()) // ROLE?
		{	
			Board board = boardService.findById(boardid);
			Collection<Link> allLinks = linkService.findAllByBoardId(boardid);
			model.addAttribute("links", allLinks);
			model.addAttribute("board", board);
			return "board.jsp";
		}
		else
		    return "index.jsp";			
 
	}
	

	@RequestMapping(value="deleteBoard")
	public String deleteBoard(@RequestParam String boardid, ModelMap model) {

		if (loggedIn()) // ROLE?
		{	
			Board board = boardService.findById(boardid);
			boardService.delete(board);
			return "redirect:/dashboard";
		}
		else
		    return "redirect:index.jsp";			
 
	}
	


	@RequestMapping(value="deleteLink")
	public String deleteLink(@RequestParam String linkid, ModelMap model) {

		if (loggedIn()) // ROLE?
		{	
			Link link = linkService.findById(linkid);
			linkService.delete(link);
			return "redirect:viewBoard?boardid="+link.getBoardId();
		}
		else
		    return "redirect:index.jsp";			
 
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
					
					//save the image to the file system
				    //File outputfile = new File(imagesDir, boardId+"_"+name+".jpg");
				    //ImageIO.write(image, "jpg", outputfile);
				    //System.out.println("Absolute Path is " + outputfile.getAbsolutePath());
				    //return outputfile.getName();
					return byteStream.toByteArray();
				} catch (IOException e) {
				    e.printStackTrace();
				}
			}
			return null;
	}
	
	
	
	@RequestMapping("createLink")
	public String createLink(@RequestParam String id, @RequestParam String url, @RequestParam String name, @RequestParam String description, @RequestParam String boardId, @RequestParam String gotolink, Model model) {
	
		if (loggedIn()) // ROLE?
		{	
			
			// validation
			if(url.isEmpty())
			{
				model.addAttribute("error", "URL is mandatory!!");
				return "addLink.jsp";			
			}
			
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
			
			
			
			if(gotolink.equalsIgnoreCase("dashboard"))
				return "redirect:/dashboard";
			else
				return "redirect:viewBoard?boardid="+boardId;
		}
		else
		   return "redirect:index.jsp";	
	}
	
	@RequestMapping("createBoard")
	public String createBoard(@RequestParam String id, @RequestParam String name, @RequestParam String description, Model model) {

		if (loggedIn()) // ROLE?
		{
			// validation
			if(name.isEmpty())
			{
				model.addAttribute("error", "Name is mandatory!!");
				return "redirect:/dashboard";
			}
			
			if(!id.isEmpty()) 		
				boardService.update(id, name, description);
			else 
				boardService.create(name, description, loggedInUser.getId());	

			Collection<Board> allBoards = boardService.findAllByUserId(loggedInUser.getId());
			model.addAttribute("boards", allBoards);	  
			return "redirect:/dashboard";
	   }
	   else
		 return "redirect:index.jsp";	
	}


}
