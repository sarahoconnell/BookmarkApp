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
@Table(name="user_roles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class UserRole {
	@Id
	private String id;
	
	@Basic
	@NotEmpty
	private String userId;
	
	@Basic
	@NotEmpty
	private String authority;
	
	
	private static final long serialVersionUID = 3L;

	public UserRole(String userId, String authority) {
		this(UUID.randomUUID().toString(), userId, authority);
	}
	public UserRole(){
		this("", "");
	}
	public UserRole(String id, String userId, String authority){
		this.id = id; 
		this.userId = userId; 
		this.authority = authority; 
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	
	
}
