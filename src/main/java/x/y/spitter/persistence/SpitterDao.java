package x.y.spitter.persistence;

import java.util.Collection;
import java.util.List;

import x.y.spitter.domain.Spitter;
import x.y.spitter.domain.Spittle;
import x.y.spitter.util.EditSpitter;
import x.y.spitter.util.ShortSpitter;
import x.y.spitter.util.Tuple;

public interface SpitterDao {

	void addSpitter(Spitter spitter);
	void saveSpitter(Spitter spitter);
	Spitter findSpitterByUserameOrEmail(String usernameOrEmail);
	void addSpittle(Spittle spittle);
	Collection<Tuple> getRecentSpittles(long id);
	void updateSpitter(String username, EditSpitter editSpitter, String avatarAction);
	List<ShortSpitter> getFollowing(String username);
	List<ShortSpitter> getFollowers(String username);
	int countFollowers(String username);
	int countFollowing(String username);
	List<ShortSpitter> searchSpitters(String query);
	void deleteSpittle(String username, long id);
	boolean confirmUserDetails(String username, String email);
	boolean updateSpitterPassword(String username, String password);
	List<ShortSpitter> getAllUsers();
	void deleteSpitter(String username);
	boolean isUsernameAvailable(String username);
}
