package ie.cit.adf.services;

import ie.cit.adf.domain.Link;
import ie.cit.adf.domain.dao.LinkRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LinkServiceImpl implements LinkService {

    @Autowired
    @Qualifier("hibernateLinkRepository")
	LinkRepository repo;

	public Link findById(String id) {
		return repo.findById(id);
	}

	
	public Collection<Link> findAll() {
		return repo.findAll();
	}

	public Link create(String url, String description, String boardId) {
		Link link = new Link();
		link.setUrl(url);
		link.setDescrption(description);
		link.setBoardId(boardId);
		repo.create(link);
		return link;
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
	public void delete(Link link) {
		repo.delete(link);
	}

}
