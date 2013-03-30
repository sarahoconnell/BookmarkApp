package ie.cit.adf.services;

import ie.cit.adf.domain.Link;

import java.util.List;

public interface LinkService {

	// GET
	List<Link> getAll();

	// POST
	Link create(String url, String description, String boardId);

	// PUT
	Link update(String id, String url, String description, String boardId);

	// GET {id}
	Link get(String id);

	// DELETE {id}
	void delete(String id);

}
