package x.y.spitter.util;

public class ShortSpitter {

	private String name;
	private String avatar;
	private String fullName;

	public ShortSpitter(String name, String avatar, String fullName) {
		this.name = name;
		this.avatar = avatar;
		this.fullName = fullName;
	}
	
	public ShortSpitter(String name, String avatar) {
		this.name = name;
		this.avatar = avatar;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
}
