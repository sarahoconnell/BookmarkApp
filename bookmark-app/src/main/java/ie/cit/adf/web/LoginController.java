package ie.cit.adf.web;

import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.User;
import ie.cit.adf.services.BoardService;
import ie.cit.adf.services.UserService;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal ) {
 
		String name = principal.getName();

		User user = userService.findByName(name);
		Collection<Board> allBoards = boardService.findAllByUserId(user.getId());
		
		model.addAttribute("user", user);
		model.addAttribute("boards", allBoards);
		return "dashboard.jsp";
 
	}
 
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
 
		return "index.jsp";
 
	}
 
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "index.jsp";
 
	}
 
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 
		return "login";
 
	}
 
}
