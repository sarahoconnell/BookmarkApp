package ie.cit.adf.domain;

import ie.cit.adf.aspect.TracingAspect;

import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.codec.Base64;


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
	
	@JsonIgnore
	@Basic 
	private byte[] image;

	  @JsonAnySetter
	  public void handleUnknown(String key, Object value) {
	    // do something: put to a Map; log a warning, whatever
	    Log log = LogFactory.getLog(Link.class);
		log.trace("handleUnknown key: "+key);
		log.trace("handleUnknown value: "+value);
	  }
	
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
		this.image = null;
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
	
	public void setImage(byte[] image){
		this.image = image;
	}
	
	public byte[] getImage(){
		return image;
	}
	
	public String getImageData(){
		if(this.image==null)
		{
			return "";
		}
		return  new String(Base64.encode(this.image));
		   //return new String(this.image);
	}
	
}
