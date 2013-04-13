package ie.cit.adf.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import ie.cit.adf.domain.Board;
import ie.cit.adf.domain.Link;
import ie.cit.adf.domain.dao.LinkRepository;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class LinkServiceImplTest {

	private LinkServiceImpl linkService;
	private LinkRepository linkRepository;

	@Before 
	public void setup() {
		linkRepository = Mockito.mock(LinkRepository.class);
		linkService = new LinkServiceImpl();
		linkService.repo = linkRepository;
	}
	
	@Test
	public void testCreateLink(){
		Link link = linkService.create("http://google.com", "Google", "Search Engine", "board1");
		String id = link.getId();
		Mockito.verify(linkRepository).create(link);
		
		assertThat(link.getUrl(), equalTo("http://google.com"));
		assertThat(link.getName(), equalTo("Google"));
		assertThat(link.getDescription(), equalTo("Search Engine"));
		assertThat(link.getId(), equalTo(id));
		assertThat(link.getBoardId(), equalTo("board1"));
	}
	
	
	
	@Test
	public void testFindAll() {  
		Link google = linkService.create("http://google.com", "Google", "Search Engine", "board1");
		Link apple = linkService.create("http://apple.com", "Apple", "Technology Giant", "board1");
		Link microsoft = linkService.create("http://microsoft.com", "Microsoft", "Another Technology Giant", "board2");
		Link notIncluded = linkService.create("http://nowhere.com", "Not Included", "", "");
		
		ArrayList<Link> links = new ArrayList<Link>();
		links.add(google); 
		links.add(apple);
		links.add(microsoft); 
		Mockito.when(linkRepository.findAll()).thenReturn(links);

		int numberOfLinks = linkService.findAll().size();
		assertEquals(numberOfLinks, 3);

		assertEquals(linkService.findAll().contains(google), true);
		assertEquals(linkService.findAll().contains(apple), true);
		assertEquals(linkService.findAll().contains(microsoft), true);
		assertEquals(linkService.findAll().contains(notIncluded),false);
		
		//ensure that the findAll method was called 5 times
		Mockito.verify(linkRepository, Mockito.times(5)).findAll();
	}
	

	@Test
	public void testFindByBoardId() {  
		Link google = linkService.create("http://google.com", "Google", "Search Engine", "board1");
		Link apple = linkService.create("http://apple.com", "Apple", "Technology Giant", "board1");
		Link microsoft = linkService.create("http://microsoft.com", "Microsoft", "Another Technology Giant", "board2");
		Link notIncluded = linkService.create("http://nowhere.com", "Not Included", "", "");
		
		ArrayList<Link> board1Links = new ArrayList<Link>();
		board1Links.add(google); 
		board1Links.add(apple);
		
		ArrayList<Link> board2Links = new ArrayList<Link>();
		board2Links.add(microsoft);
		
		Mockito.when(linkRepository.findAllByBoardId(google.getBoardId())).thenReturn(board1Links);
		Mockito.when(linkRepository.findAllByBoardId(microsoft.getBoardId())).thenReturn(board2Links);
		Mockito.when(linkRepository.findAllByBoardId(notIncluded.getBoardId())).thenReturn(null);
		
		assertEquals(linkService.findAllByBoardId(google.getBoardId()), board1Links);
		assertEquals(linkService.findAllByBoardId(microsoft.getBoardId()), board2Links);
		assertThat(linkService.findAllByBoardId(notIncluded.getBoardId()), equalTo(null));
		assertThat(linkService.findAllByBoardId("random").size(), equalTo(0));
		
	
		//ensure that the find method in userRepostory was called the correct amount of times
		Mockito.verify(linkRepository, Mockito.times(1)).findAllByBoardId(microsoft.getBoardId());
		Mockito.verify(linkRepository, Mockito.times(1)).findAllByBoardId(google.getBoardId());
		Mockito.verify(linkRepository, Mockito.times(1)).findAllByBoardId(notIncluded.getBoardId());
		Mockito.verify(linkRepository, Mockito.times(1)).findAllByBoardId("random");

	
	}
	
	@Test
	public void testFindById() {  
		Link google = linkService.create("http://google.com", "Google", "Search Engine", "board1");
		Link apple = linkService.create("http://apple.com", "Apple", "Technology Giant", "board1");
		Link microsoft = linkService.create("http://microsoft.com", "Microsoft", "Another Technology Giant", "board2");
		Link notIncluded = linkService.create("http://nowhere.com", "Not Included", "", "");
		
		Mockito.when(linkService.findById(google.getId())).thenReturn(google);
		Mockito.when(linkService.findById(apple.getId())).thenReturn(apple);
		Mockito.when(linkService.findById(microsoft.getId())).thenReturn(microsoft);
		Mockito.when(linkService.findById(notIncluded.getId())).thenReturn(null);
		
		assertEquals(linkService.findById(google.getId()), google);
		assertEquals(linkService.findById(apple.getId()), apple);
		assertEquals(linkService.findById(microsoft.getId()), microsoft);
		assertEquals(linkService.findById(notIncluded.getId()), null);
		assertEquals(linkService.findById("random"), null);
	
		//ensure that the find method in userRepostory was called the correct amount of times
		Mockito.verify(linkRepository, Mockito.times(1)).findById(google.getId());
		Mockito.verify(linkRepository, Mockito.times(1)).findById(apple.getId());
		Mockito.verify(linkRepository, Mockito.times(1)).findById(microsoft.getId());
		Mockito.verify(linkRepository, Mockito.times(1)).findById(notIncluded.getId());
		Mockito.verify(linkRepository, Mockito.times(1)).findById("random");

	
	}

	@Test
	public void testUpdate() {  
		
		Link google = linkService.create("http://google.com", "Google", "Search Engine", "board1");
		Link apple = linkService.create("http://apple.com", "Apple", "Technology Giant", "board1");
		
		Mockito.when(linkRepository.findById(google.getId())).thenReturn(google);
		Mockito.when(linkRepository.findById(apple.getId())).thenReturn(apple);
		
		
		google.setName("Google Search");
		linkService.update(google.getId(),  google.getUrl(), google.getName(), google.getDescription(),  google.getBoardId());
		Mockito.verify(linkRepository, Mockito.times(1)).update(google);
		assertEquals(google.getName().equals("Google Search"), true);
		
		apple.setDescription("iEverything");
		linkService.update(apple.getId(),  apple.getUrl(), apple.getName(), apple.getDescription(),  apple.getBoardId());
		Mockito.verify(linkRepository, Mockito.times(1)).update(apple);
		assertEquals(apple.getDescription().equals("iEverything"), true);
		
		
		
		
	}


	@Test
	public void testDelete() {  
		Link google = linkService.create("http://google.com", "Google", "Search Engine", "board1");
		linkService.delete(google);
		Mockito.verify(linkRepository).delete(google);        
        
	}
	
}
