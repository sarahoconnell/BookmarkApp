package ie.cit.adf.services;

import ie.cit.adf.domain.Link;

import java.util.Collection;

public interface LinkService {

	// GET {id}
	Link findById(String id);
	
	// GET
	Collection<Link> findAll();

	// POST
	Link create(String url, String description, String boardId);

	// PUT
	Link update(String id, String url, String description, String boardId);

	// DELETE {id}
	void delete(Link link);

}
