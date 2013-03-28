package ie.cit.adf.services;

import ie.cit.adf.domain.Bookmark;
import ie.cit.adf.domain.dao.BookmarkRepository;

import java.util.List;

public class BookmarkServiceImpl implements BookmarkService {
	private BookmarkRepository repo;

	public BookmarkServiceImpl(BookmarkRepository repo) {
		this.repo = repo;
	}

	public List<Bookmark> getAllBookmarks() {
		return repo.getAll();
	}

	public Bookmark createNewBookmark(String text) {
		Bookmark bookmark = new Bookmark();
		bookmark.setText(text);
		repo.add(bookmark);
		return bookmark;
	}

	public void close(String bookmarkId) {
		Bookmark bookmark = repo.findById(bookmarkId);
		if (bookmark != null) {
			bookmark.setDone(true);
			repo.update(bookmark);
		}
	}

	public void open(String bookmarkId) {
		Bookmark bookmark = repo.findById(bookmarkId);
		if (bookmark != null) {
			bookmark.setDone(false);
			repo.update(bookmark);
		}
	}

	public Bookmark get(String bookmarkId) {
		return repo.findById(bookmarkId);
	}

	@Override
	public void delete(String bookmarkId) {
		repo.delete(bookmarkId);
	}
}
