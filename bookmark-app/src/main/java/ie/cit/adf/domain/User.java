package ie.cit.adf.domain;

import java.util.Set;
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
@Table(name="USERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
	@Id
	private String id;
	@Basic
	@NotEmpty
	private String name;
	@Basic
	@NotEmpty
	private String password;
	@Basic
	private String twitterId;
	
	@Basic
	@Column(nullable=false, columnDefinition="TINYINT(1)")
	private boolean enabled;
	
	private static final long serialVersionUID = 3L;

	public User(String name) {
		this(UUID.randomUUID().toString(), name, "", "",  true);
	}
	public User(){
		this("");
	}
	public User(String id, String name, String password, String twitterId, boolean enabled){
		this.id = id; 
		this.name = name; 
		this.password = password; 
		this.twitterId = twitterId;
		this.enabled = enabled;
	}

	
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}
}
