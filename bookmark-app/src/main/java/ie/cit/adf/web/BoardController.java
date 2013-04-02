package ie.cit.adf.web;

import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.User;
import ie.cit.adf.services.BoardService;
import ie.cit.adf.services.UserService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {  
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;

	@RequestMapping("createBoard")
	public String create(@RequestParam String name, @RequestParam String description, Model model) {
		// logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated()) // ROLE?
		{
			// validation
			if(name.isEmpty())
			{
				model.addAttribute("error", "Name is mandatory!!");
				return "dashboard.jsp";
			}		
		
			String userName = auth.getName();
			User user = userService.findByName(userName);
			Board newBoard = boardService.create(name, description, user.getId());
			Collection<Board> allBoards = boardService.findAllByUserId(user.getId());
			model.addAttribute("boards", allBoards);
			return "dashboard.jsp";
	   }
	   else
		 return "index.jsp";	
	}


}
