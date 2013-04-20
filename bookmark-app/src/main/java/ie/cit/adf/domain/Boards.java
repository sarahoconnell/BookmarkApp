package ie.cit.adf.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class Boards {
	
	@JsonProperty("boardList")
	private List<Board> boards;

	public List<Board> getBoards() {
		return boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}
	
	

}
