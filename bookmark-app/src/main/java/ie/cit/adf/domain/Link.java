package ie.cit.adf.domain;

import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="link")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Link  {
	@Id
	private String id;
	@Basic
	@NotEmpty
	private String url;
	@Basic
	private String description;
	@Basic
	private String name;
	@Basic
	private String boardId;
	

	public Link() {
		this("");
	}
	public Link(String url){
		this(UUID.randomUUID().toString(), url, "", "", "");
	}
	public Link(String id, String url, String name, String description, String boardId){
		this.id = id;
		this.url = url;
		this.name = name; 
		this.description = description; 
		this.boardId = boardId;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	
}
