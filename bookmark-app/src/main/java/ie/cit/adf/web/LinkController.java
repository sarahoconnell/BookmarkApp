package ie.cit.adf.web;

import ie.cit.adf.domain.Link;
import ie.cit.adf.services.LinkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LinkController {
	@Autowired
	private LinkService linkService;

	@RequestMapping("add")
	public String register() {
		return "../addLink.jsp";
	}

	@RequestMapping("createLink")
	public String create(@RequestParam String url, @RequestParam String description, @RequestParam String boardId, Model model) {
		
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
		return "dashboard.jsp";
	}


}
