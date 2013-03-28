package ie.cit.adf.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bookmarks {
	public List<Bookmark> bookmarks = new ArrayList<Bookmark>();

	public Bookmarks() {
	}

	public Bookmarks(List<Bookmark> bookmarks) {
		super();
		this.bookmarks = bookmarks;
	}
}
