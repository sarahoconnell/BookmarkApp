package ie.cit.adf.services;

import ie.cit.adf.domain.Link;
import ie.cit.adf.domain.dao.LinkRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LinkServiceImpl implements LinkService {

    @Autowired
    @Qualifier("hibernateLinkRepository")
	LinkRepository repo;

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public Link findById(String id) {
		return repo.findById(id);
	}

	// unsecured - for public boards
	public Collection<Link> findAllByBoardId(String boardId) {
		return repo.findAllByBoardId(boardId);
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public Collection<Link> findAll() {
		return repo.findAll();
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public Link create(String url, String name, String description, String boardId, byte[] image) {
		Link link = new Link();
		link.setUrl(url);
		link.setName(name);
		link.setDescription(description);
		link.setBoardId(boardId);
		link.setImage(image);
		repo.create(link);
		return link;
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public Link create(Link link) {
		repo.create(link);
		return link;
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public Link update(String id, String url, String name, String description, String boardId, byte[] image) {
		Link link = repo.findById(id);
		link.setUrl(url);
		link.setName(name);
		link.setDescription(description);
		link.setBoardId(boardId);
		link.setImage(image);
		repo.update(link);
		return link;		
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public Link update(Link link) {
		repo.update(link);
		return link;		
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public void delete(Link link) {
		repo.delete(link);
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public void delete(String id) {
		Link link = repo.findById(id);
		repo.delete(link);
	}
}
