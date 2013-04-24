package ie.cit.adf.web;

import ie.cit.adf.constants.Constants;
import ie.cit.adf.domain.User;
import ie.cit.adf.services.BoardService;
import ie.cit.adf.services.UserService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController extends BaseController  {

	@Autowired
	private UserService userService;
	@Autowired
	private BoardService boardService;
				
	// NOT SECURED - VISIBLE TO ALL
	@RequestMapping(value=Constants.registerUserMapping, method = RequestMethod.GET)
	public String register() {
		return Constants.registerPage;
	}

	// NOT SECURED - VISIBLE TO ALL
	@RequestMapping(value=Constants.createUserMapping, method = RequestMethod.POST)
	public String create(@RequestParam String name, @RequestParam String password, @RequestParam String password2, @RequestParam String twitterId, Model model) {
		
		if(name.isEmpty() || password.isEmpty())
		{
			model.addAttribute(Constants.error, Constants.namePasswordMandatory);
			return Constants.registerPage;			
		}
		if(!password.equals(password2))
		{
			model.addAttribute(Constants.error, Constants.passwordsDoNotMatch);
			return Constants.registerPage;			
		}
		if(userService.findByName(name)!=null)
		{
			model.addAttribute(Constants.error, Constants.userAlreadyExists);
			return Constants.registerPage;						
		}
					
		User newUser = userService.create(name, password, twitterId);
		userService.createRole(newUser, "ROLE_USER");		
		boardService.create("default", "default", newUser.getId(), "icon-star", false);
		model.addAttribute(Constants.message, Constants.registrationSuccess);
		return Constants.registerPage;
	}


	@Secured("ROLE_ADMIN")
	@RequestMapping(value = Constants.adminMapping, method = RequestMethod.GET)
	public String showAdmin(ModelMap model) {
 
		if(loggedIn()){
			Collection<User> allUsers = userService.findAll();
			model.addAttribute(Constants.users, allUsers);
			model.addAttribute(Constants.currentUser, loggedInUser);
			return Constants.adminPage;
		}
		else
		    return Constants.indexMapping;	
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value=Constants.removeUserMapping, method = RequestMethod.POST)
	public String removeUser(@RequestParam String userId, ModelMap model) {

		if (loggedIn()) // ROLE?
		{	
			User user = userService.findById(userId);
		    userService.delete(user);
			return Constants.redirect+Constants.adminMapping;
		}
		else
		    return Constants.redirect+Constants.indexMapping;			 
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value=Constants.toggleUserEnableMapping, method = RequestMethod.POST)
	public String toggleUserEnable(@RequestParam String userId, @RequestParam boolean enabled, ModelMap model) {

		if (loggedIn()) // ROLE?
		{	
			User user = userService.findById(userId);
		    user.setEnabled(enabled);
		    userService.update(user);
			return Constants.redirect+Constants.adminMapping;
		}
		else
		    return Constants.redirect+Constants.indexMapping;	
	}
}
