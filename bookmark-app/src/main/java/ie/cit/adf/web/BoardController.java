package ie.cit.adf.web;

import java.util.List;

import ie.cit.adf.domain.Board;
import ie.cit.adf.services.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("createBoard")
	public String create(@RequestParam String name, @RequestParam String description, @RequestParam String userId, Model model) {
		
		// validation
		if(name.isEmpty())
		{
			model.addAttribute("error", "Name is mandatory!!");
			return "dashboard.jsp";
		}		
		
		Board newBoard = boardService.create(name, description, userId);
		List<Board> allBoards = boardService.getAll(userId);
		model.addAttribute("boards", allBoards);
		return "dashboard.jsp";
	}


}
