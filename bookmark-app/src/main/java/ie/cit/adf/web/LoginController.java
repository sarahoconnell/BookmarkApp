package ie.cit.adf.web;

import ie.cit.adf.constants.Constants;
import ie.cit.adf.services.BoardService;
import ie.cit.adf.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

	@RequestMapping(value=Constants.indexMapping, method = RequestMethod.GET)
	public String index(ModelMap model) {
		return Constants.indexPage; 
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value=Constants.welcomeMapping, method = RequestMethod.GET)
	public String welcome(ModelMap model) {
 
		try{			
			if(isAdmin())
				return Constants.adminMapping;
			
			return Constants.dashboardMapping;
		}
		catch(Exception e)
		{
			model.addAttribute(Constants.error, "true");
			return Constants.indexMapping;
		} 
	}
  
	@RequestMapping(value=Constants.loginfailedMapping, method = RequestMethod.GET)
	public String loginerror(ModelMap model) { 
		model.addAttribute(Constants.error, "true");
		return Constants.indexMapping;
	}
 
	@RequestMapping(value=Constants.logoutMapping, method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return Constants.indexMapping;
	}

	@RequestMapping(value=Constants.loginMapping, method = RequestMethod.GET)
	public String login(ModelMap model) {
		return Constants.indexMapping;
	}
}
