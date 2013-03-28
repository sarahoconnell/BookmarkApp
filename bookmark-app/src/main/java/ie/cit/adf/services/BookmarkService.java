package ie.cit.adf.services;

import ie.cit.adf.domain.Bookmark;

import java.util.List;

public interface BookmarkService {

	List<Bookmark> getAllBookmarks();

	Bookmark createNewBookmark(String text);

	void close(String bookmarkId);

	void open(String bookmarkId);

	Bookmark get(String bookmarkId);

	void delete(String bookmarkId);

}
