package ie.cit.adf.web;

import ie.cit.adf.services.BoardService;
import ie.cit.adf.services.UserService;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController extends BaseController{

	@Autowired
	private UserService userService;
	@Autowired
	private BoardService boardService;

	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index(ModelMap model) {
		return "index.jsp"; 
	}
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String welcome(ModelMap model, Principal principal ) {
 
		try{
			
			if(isAdmin())
				return "/admin";
			
			return "/dashboard";
		}
		catch(Exception e)
		{
			model.addAttribute("error", "true");
			return "/index";
		} 
	}
  
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) { 
		model.addAttribute("error", "true");
		return "/index"; 
	}
 
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "/index"; 
	}

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "/index"; 
	}
}
