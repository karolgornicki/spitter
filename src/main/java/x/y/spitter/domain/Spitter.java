package x.y.spitter.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;


@Entity
public class Spitter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long.")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric with no spaces.")
	private String username;
	@Size(min = 6, max = 32, message = "The password must be at least 6 characters long.")
	private String password;
	@Email
	private String email;
	@Size(min = 3, max = 50, message = "Your full name must be between 3 and 50 characters long.")
	private String fullName;
	@OneToMany(mappedBy = "author")
	private Collection<Spittle> spittleList = new ArrayList<>();
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Spitter> spittersIFollow = new ArrayList<>();
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "spittersIFollow")
	private Collection<Spitter> followers = new ArrayList<>();
	private String avatar;
	@Size(max = 140)
	private String description;
	private String grantedAuthority;
	
	public String getGrantedAuthority() {
		return grantedAuthority;
	}
	public void setGrantedAuthority(String grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Collection<Spitter> getSpittersIFollow() {
		return spittersIFollow;
	}
	public void setSpittersIFollow(Collection<Spitter> spittersIFollow) {
		this.spittersIFollow = spittersIFollow;
	}
	public Collection<Spitter> getFollowers() {
		return followers;
	}
	public void setFollowers(Collection<Spitter> followers) {
		this.followers = followers;
	}
	public Collection<Spittle> getSpittleList() {
		return spittleList;
	}
	public void setSpittleList(Collection<Spittle> spittleList) {
		this.spittleList = spittleList;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
