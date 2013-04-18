package ie.cit.adf.web;

import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.User;
import ie.cit.adf.services.BoardService;
import ie.cit.adf.services.UserService;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private BoardService boardService;

	@RequestMapping("registerUser")
	public String register() {
		return "register.jsp";
	}

	@RequestMapping("createUser")
	public String create(@RequestParam String name, @RequestParam String password, @RequestParam String password2, @RequestParam String twitterId, Model model) {
		// validation
		if(name.isEmpty() || password.isEmpty())
		{
			model.addAttribute("error", "Name & Password are mandatory!!");
			return "register.jsp";			
		}
		if(!password.equals(password2))
		{
			model.addAttribute("error", "Passwords do not match!!");
			return "register.jsp";			
		}
		if(userService.findByName(name)!=null)
		{
			model.addAttribute("error", "User already exists!!");
			return "register.jsp";						
		}
					
		User newUser = userService.create(name, password, twitterId);
		userService.createRole(newUser, "ROLE_USER");		
		boardService.create("default", "default", newUser.getId(), "fav.png");

		model.addAttribute("message", "Registration successful! Please login.....");
		return "register.jsp";
	}

}
