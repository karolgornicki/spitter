package x.y.spitter.service;

import java.util.List;

import x.y.spitter.domain.Spitter;
import x.y.spitter.util.EditSpitter;
import x.y.spitter.util.ShortSpitter;

public interface SpitterService {

	void addSpitter(Spitter spitter);
	Spitter getSpitterByUsername(String username);
	void addSpitterToFollower(Spitter spitterMe, Spitter spitterToFollow);
	void removeSpitterFromFollowers(Spitter spitterMe, String username);
	boolean isSpitterFollowedBy(Spitter spitterMe, String username);
	void updateSpitter(EditSpitter editSpitter);
	List<ShortSpitter> getSpittersIFollow();
	List<ShortSpitter> getSpittersWhoFollowMe();
	int countFollowers();
	int countFollowing();
	List<ShortSpitter> searchSpitters(String query);
	void remindPassword(String username, String email);
	boolean confirmUserDetails(String username, String email);
	List<ShortSpitter> getAllUsers();
	void deleteSpitter(String username);
	boolean isUsernameAvailable(String username);
}
