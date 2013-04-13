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
@Table(name="board")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Board {
	

	@Id
	private String id;
	@Basic
	@NotEmpty
	private String name;
	@Basic
	private String description;
	@Basic 
	private String userId;
	
	public Board(){
		this("");
	}
	public Board(String name){
		this(UUID.randomUUID().toString(), name, "", "");
	}
	public Board(String id, String name, String description, String userId){
		setId(id);
		this.name = name;
		this.description = description;
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
