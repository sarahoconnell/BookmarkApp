package ie.cit.adf.domain;

import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
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
	@Basic 
	private String img;

	@Basic
	@Column(nullable=false, columnDefinition="TINYINT(1)")
	private boolean isPublic;
	
	public Board(){
		this("");
	}
	public Board(String name){
		this(UUID.randomUUID().toString(), name, "", "", "", false);
	}
	public Board(String id, String name, String description, String userId, String img, boolean isPublic){
		setId(id);
		this.name = name;
		this.description = description;
		this.userId = userId;
		this.img = img;
		this.isPublic = isPublic;
	}
	
	public boolean getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	

}
