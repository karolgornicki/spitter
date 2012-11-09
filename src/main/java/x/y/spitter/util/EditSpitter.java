package x.y.spitter.util;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class EditSpitter {

	@Size(min = 3, max = 50, message = "Your full name must be between 3 and 50 characters long.")
	private String fullName;
	@Size(min = 6, max = 32, message = "The password must be at least 6 characters long.")
	private String password;
	@Email
	private String email;
	private CommonsMultipartFile file;
	@Size(max = 140, message = "The description must be at most 140 characters long.s")
	private String description;
	private boolean removeAvatar;
	
	public boolean isRemoveAvatar() {
		return removeAvatar;
	}
	public void setRemoveAvatar(boolean removeAvatar) {
		this.removeAvatar = removeAvatar;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
