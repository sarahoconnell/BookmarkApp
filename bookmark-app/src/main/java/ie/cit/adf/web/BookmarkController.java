package ie.cit.adf.web;

import ie.cit.adf.services.BookmarkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookmarkController {
	@Autowired
	private BookmarkService bookmarkService;

	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("bookmarks", bookmarkService.getAllBookmarks());
		return "bookmarks.jsp";
	}

	@RequestMapping("create")
	public String create(@RequestParam String text, Model model) {
		bookmarkService.createNewBookmark(text);
		model.addAttribute("bookmarks", bookmarkService.getAllBookmarks());
		return "bookmarks.jsp";
	}

	@RequestMapping("close")
	public String close(@RequestParam String bookmarkId, Model model) {
		bookmarkService.close(bookmarkId);
		model.addAttribute("bookmarks", bookmarkService.getAllBookmarks());
		return "bookmarks.jsp";
	}

	@RequestMapping("open")
	public String open(@RequestParam String bookmarkId, Model model) {
		bookmarkService.open(bookmarkId);
		model.addAttribute("bookmarks", bookmarkService.getAllBookmarks());
		return "bookmarks.jsp";
	}
}
