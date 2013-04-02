package ie.cit.adf.web;

import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.Link;
import ie.cit.adf.domain.User;
import ie.cit.adf.services.BoardService;
import ie.cit.adf.services.LinkService;
import ie.cit.adf.services.UserService;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController extends BaseController{  
	@Autowired
	private BoardService boardService;
	@Autowired  
	private LinkService linkService;

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
	
	@RequestMapping("createLink")
	public String create(@RequestParam String url, @RequestParam String description, @RequestParam String boardId, Model model) {
	
		if (loggedIn()) // ROLE?
		{	
			// validation
			if(url.isEmpty())
			{
				model.addAttribute("error", "URL is mandatory!!");
				return "addLink.jsp";			
			}
			
			if(boardId.isEmpty())
			{
				// get default boardID
			}
			
			Link newLink = linkService.create(url, description, boardId);
			model.addAttribute("link", newLink);
			return "/dashboard";
		}
		else
		   return "index.jsp";	
	}
	
	@RequestMapping("createBoard")
	public String create(@RequestParam String name, @RequestParam String description, Model model) {

		if (loggedIn()) // ROLE?
		{
			// validation
			if(name.isEmpty())
			{
				model.addAttribute("error", "Name is mandatory!!");
				return "/dashboard";
			}		
		
			Board newBoard = boardService.create(name, description, loggedInUser.getId());
			Collection<Board> allBoards = boardService.findAllByUserId(loggedInUser.getId());
			model.addAttribute("boards", allBoards);
			return "/dashboard";
	   }
	   else
		 return "index.jsp";	
	}


}
