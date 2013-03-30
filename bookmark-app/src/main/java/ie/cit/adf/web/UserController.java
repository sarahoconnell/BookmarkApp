package ie.cit.adf.web;

import ie.cit.adf.domain.User;
import ie.cit.adf.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("register")
	public String register() {
		return "../register.jsp";
	}

	@RequestMapping("create")
	public String create(@RequestParam String name, @RequestParam String password, @RequestParam String password2, @RequestParam String twitterId, Model model) {
		// validation
		if(name.isEmpty() || password.isEmpty())
		{
			model.addAttribute("error", "Name & Password are mandatory!!");
			return "../register.jsp";			
		}
		if(!password.equals(password2))
		{
			model.addAttribute("error", "Passwords do not match!!");
			return "../register.jsp";			
		}
		
		User newUser = userService.create(name, password, twitterId);
		model.addAttribute("user", newUser);
		return "../dashboard.jsp";
	}


}
