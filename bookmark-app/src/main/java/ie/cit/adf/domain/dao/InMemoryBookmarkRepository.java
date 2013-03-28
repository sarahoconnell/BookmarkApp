package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Bookmark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryBookmarkRepository implements BookmarkRepository {
	private Map<String, Bookmark> data = new HashMap<String, Bookmark>();

	@Override
	public Bookmark findById(String id) {
		return data.get(id);
	}

	@Override
	public List<Bookmark> getAll() {
		return new ArrayList<Bookmark>(data.values());
	}

	@Override
	public void add(Bookmark bookmark) {
		data.put(bookmark.getId(), bookmark);
	}

	@Override
	public void delete(String bookmarkId) {
		data.remove(bookmarkId);
	}

	@Override
	public void update(Bookmark bookmark) {
		data.put(bookmark.getId(), bookmark);
	}

}
