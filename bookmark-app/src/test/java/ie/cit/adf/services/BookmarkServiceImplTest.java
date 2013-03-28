package ie.cit.adf.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import ie.cit.adf.domain.Bookmark;
import ie.cit.adf.domain.dao.BookmarkRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BookmarkServiceImplTest {

	private BookmarkServiceImpl tested;
	private BookmarkRepository bookmarkRepository;

	@Before
	public void setup() {
		bookmarkRepository = Mockito.mock(BookmarkRepository.class);
		tested = new BookmarkServiceImpl(bookmarkRepository);
	}

	@Test
	public void testCreateBookmark() {  
		Bookmark createNewBookmark = tested.createNewBookmark("Bookmark 1");
		Mockito.verify(bookmarkRepository).add(createNewBookmark);
		assertThat(createNewBookmark.getText(), equalTo("Bookmark 1"));
	}

}
