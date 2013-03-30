package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Link;

import java.util.List;

public interface LinkRepository {

	Link findById(String id);

	List<Link> getAll();

	void add(Link link);

	void delete(String linkId);

	void update(Link linkId);

}
