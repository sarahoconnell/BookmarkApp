package ie.cit.adf.services;

import ie.cit.adf.domain.Link;
import ie.cit.adf.domain.dao.LinkRepository;

import java.util.List;

public class LinkServiceImpl implements LinkService {
	private LinkRepository repo;

	public LinkServiceImpl(LinkRepository repo) {
		this.repo = repo;
	}

	public List<Link> getAll() {
		return repo.getAll();
	}

	public Link create(String url, String description, String boardId) {
		Link link = new Link();
		link.setUrl(url);
		link.setDescrption(description);
		link.setBoardId(boardId);
		repo.add(link);
		return link;
	}


	public Link get(String id) {
		return repo.findById(id);
	}

	public Link update(String id, String url, String description, String boardId) {
		Link link = repo.findById(id);
		link.setUrl(url);
		link.setDescrption(description);
		link.setBoardId(boardId);
		repo.update(link);
		return link;
		
	}

	@Override
	public void delete(String id) {
		repo.delete(id);
	}

}
