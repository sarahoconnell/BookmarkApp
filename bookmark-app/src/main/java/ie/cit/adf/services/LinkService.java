package ie.cit.adf.services;

import ie.cit.adf.domain.Link;

import java.util.Collection;

public interface LinkService {

	// GET {id}
	Link findById(String id);

	Collection<Link> findAllByBoardId(String boardId);
	
	// GET
	Collection<Link> findAll();

	// POST
	Link create(String url, String name, String description, String boardId, byte[] image);

	// PUT
	Link update(String id, String url, String name, String description, String boardId, byte[] image);

	// DELETE {id}
	void delete(String id);
	void delete(Link link);

}
