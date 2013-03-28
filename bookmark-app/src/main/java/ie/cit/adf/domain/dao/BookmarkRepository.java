package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Bookmark;

import java.util.List;

public interface BookmarkRepository {

	Bookmark findById(String id);

	List<Bookmark> getAll();

	void add(Bookmark bookmark);

	void delete(String bookmarkId);

	void update(Bookmark bookmark);

}
