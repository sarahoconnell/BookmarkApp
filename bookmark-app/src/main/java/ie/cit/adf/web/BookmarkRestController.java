package ie.cit.adf.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("api")  
public class BookmarkRestController {
/*	@Autowired
	private BookmarkService bookmarkService;

	// curl -X GET -i http://localhost:8080/bookmark-app/api/bookmark
	@RequestMapping(value = "bookmark", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Bookmarks bookmarks() {
		return new Bookmarks(bookmarkService.getAllBookmarks());
	}

	// curl -X GET -i http://localhost:8080/bookmark-app/api/bookmark/{id}
	@RequestMapping(value = "bookmark/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Bookmark bookmark(@PathVariable String id) {
		Bookmark bookmark = bookmarkService.get(id);
		if (bookmark == null)
			throw new NotFoundException();
		return bookmark;
	}

	// curl -X POST -i http://localhost:8080/bookmark-app/api/bookmark?text=BookmarkItem1
	@RequestMapping(value = "bookmark", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@RequestParam String text, HttpServletRequest req,
			HttpServletResponse resp) {
		Bookmark bookmark= bookmarkService.createNewBookmark(text);
		StringBuffer url = req.getRequestURL().append("/{id}");
		UriTemplate uriTemplate = new UriTemplate(url.toString());
		resp.addHeader("location", uriTemplate.expand(bookmark.getId()).toASCIIString());
	}

	// curl -X DELETE -i http://localhost:8080/bookmark-app/api/bookmark/{id}
	@RequestMapping(value = "bookmark/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		bookmarkService.delete(id);
	}

	// curl -X PUT -i http://localhost:8080/bookmark-app/api/bookmark/{id} -d
	// '{"text":"bookmark Item Text","done":true}'
	@RequestMapping(value = "bookmark/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable String id, @RequestBody Bookmark bookmark) {
		Bookmark existing = bookmarkService.get(id);
		if (existing == null)
			throw new NotFoundException();
		existing.setText(bookmark.getText());
		existing.setDone(bookmark.isDone());
	}
*/
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}
