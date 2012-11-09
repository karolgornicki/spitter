package x.y.spitter.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import x.y.spitter.domain.Spitter;
import x.y.spitter.domain.Spittle;
import x.y.spitter.util.EditSpitter;
import x.y.spitter.util.ShortSpitter;
import x.y.spitter.util.Tuple;

@Service("hibernateSpitterDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class HibernateSpitterDao implements SpitterDao {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public HibernateSpitterDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void addSpitter(Spitter spitter) {
		currentSession().save(spitter);

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void saveSpitter(Spitter spitter) {
		currentSession().update(spitter);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public Spitter findSpitterByUserameOrEmail(String usernameOrEmail) {
		
		Query query = currentSession().createQuery("from Spitter where username = :usernameOrEmail");
		query.setString("usernameOrEmail", usernameOrEmail);
		@SuppressWarnings("unchecked")
		List<Spitter> users = query.list();
		if(users.size() != 0) {
			return users.get(0);
		} 
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void addSpittle(Spittle spittle) {
		currentSession().save(spittle);		
	}

	@Override
	public Collection<Tuple> getRecentSpittles(long id) {
		
		Collection<Tuple> recentSpittles = new ArrayList<>();
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Spitter spitterMe = (Spitter) session.get(Spitter.class, id);
		String username = spitterMe.getUsername();
		
		Collection<Spittle> spittleList = spitterMe.getSpittleList();
		for(Spittle spittle : spittleList) {
			recentSpittles.add(new Tuple(spittle.getText(), spittle.getCreatedAt(), username, spittle.getAuthor().getAvatar(), spittle.getId()));
		}
		
		session.getTransaction().commit();
		session.close();
		
		return recentSpittles;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void updateSpitter(String username, EditSpitter editSpitter, String avatarAction) {
		
		Session session = currentSession();
		
		Query query = session.createQuery("from Spitter where username = :username");
		query.setString("username", username);
		@SuppressWarnings("unchecked")
		List<Spitter> users = query.list();
		Spitter spitter = users.get(0);
		
		switch(avatarAction) {
		case "update" : 
			spitter.setAvatar(spitter.getId() + ".jpg");
			break;
		case "maintain" :
			//Don't impose any changes.
			break;
		case "delete" :
			spitter.setAvatar("default.jpg");
		}
		
		spitter.setFullName(editSpitter.getFullName());
		spitter.setPassword(editSpitter.getPassword());
		spitter.setEmail(editSpitter.getEmail());
		spitter.setDescription(editSpitter.getDescription());
		
		session.update(spitter);		
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public List<ShortSpitter> getFollowing(String username) {
		
		Spitter spitter;
		List<ShortSpitter> followingList = new ArrayList<>();
		Session session = currentSession();
		Query query = session.createQuery("from Spitter where username = :username");
		query.setString("username", username);
		@SuppressWarnings("unchecked")
		List<Spitter> spitters = query.list();
		try {
			spitter = spitters.get(0);
		} catch(NullPointerException e) {
			return null;
		}
		for(Spitter user : spitter.getSpittersIFollow()) {
			followingList.add(new ShortSpitter(user.getUsername(), user.getAvatar()));
		}
		return followingList;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public List<ShortSpitter> getFollowers(String username) {
		
		Spitter spitter;
		List<ShortSpitter> followersList = new ArrayList<>();
		Session session = currentSession();
		Query query = session.createQuery("from Spitter where username = :username");
		query.setString("username", username);
		@SuppressWarnings("unchecked")
		List<Spitter> spitters = query.list();
		try {
			spitter = spitters.get(0);
		} catch(NullPointerException e) {
			return null;
		}
		for(Spitter user : spitter.getFollowers()) {
			followersList.add(new ShortSpitter(user.getUsername(), user.getAvatar()));
		}
		
		return followersList;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public int countFollowers(String username) {
		
		Spitter spitter;
		Session session = currentSession();
		Query query = session.createQuery("from Spitter where username = :username");
		query.setString("username", username);
		@SuppressWarnings("unchecked")
		List<Spitter> users = query.list();
		try {
			spitter = users.get(0);
		} catch(NullPointerException e) {
			return 0;
		}
		
		return spitter.getFollowers().size();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public int countFollowing(String username) {
		
		Spitter spitter;
		Session session = currentSession();
		Query query = session.createQuery("from Spitter where username = :username");
		query.setString("username", username);
		@SuppressWarnings("unchecked")
		List<Spitter> users = query.list();
		try {
			spitter = users.get(0);
		} catch(NullPointerException e) {
			return 0;
		}
		
		return spitter.getSpittersIFollow().size();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public List<ShortSpitter> searchSpitters(String query) {
		
		List<ShortSpitter> result = new ArrayList<>();
		Session session = currentSession();

		Criteria crit = session.createCriteria(Spitter.class);
		crit.add(Restrictions.ilike("fullName", query, MatchMode.ANYWHERE));
		@SuppressWarnings("unchecked")
		List<Spitter> users = crit.list();
		
		//Remove duplicates. Caused by the eager fetch settings in the Spitter.
		HashSet<Spitter> hashSet = new HashSet<>();
		hashSet.addAll(users);
		users.clear();
		users.addAll(hashSet);
		
		if(users != null) {
			for(Spitter item : users) {
				result.add(new ShortSpitter(item.getUsername(), item.getAvatar(), item.getFullName()));
			}
		}
		System.out.println("size: " + result.size());
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void deleteSpittle(String username, long id) {
		
		Session session = currentSession();
		Spittle spittle = (Spittle) session.get(Spittle.class, id);
		if(spittle.getAuthor().getUsername().equals(username)) {
			session.delete(spittle);
		} else {
			return;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public boolean confirmUserDetails(String username, String email) {
		
		Query query = currentSession().createQuery("from Spitter where username = :username and email = :email");
		query.setString("username", username);
		query.setString("email", email);
		@SuppressWarnings("unchecked")
		List<Spitter> spitters = query.list();
		if(spitters.size() == 1) {
			return true;
		}
		return false;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public boolean updateSpitterPassword(String username, String password) {
		
		Query query = currentSession().createQuery("from Spitter where username = :username");
		query.setString("username", username);
		@SuppressWarnings("unchecked")
		List<Spitter> spitters = query.list();
		try {
			spitters.get(0).setPassword(password);
		} catch(NullPointerException e) {
			return false;
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public List<ShortSpitter> getAllUsers() {
		
		List<ShortSpitter> results = new ArrayList<>();
		Query query = currentSession().createQuery("from Spitter");
		@SuppressWarnings("unchecked")
		List<Spitter> userList = query.list();
		for(Spitter user : userList) {
			results.add(new ShortSpitter(user.getUsername(), user.getAvatar(), user.getFullName()));
		}
		
		return results;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void deleteSpitter(String username) {
		
		Session session = currentSession();
		Query query = session.createQuery("from Spitter where username = :username");
		query.setString("username", username);
		@SuppressWarnings("unchecked")
		List<Spitter> users = query.list();
		session.delete(users.get(0));		
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public boolean isUsernameAvailable(String username) {
		
		Query query = currentSession().createQuery("from Spitter where username = :username");
		query.setString("username", username);
		@SuppressWarnings("unchecked")
		List<Spitter> users = query.list();
		return users.isEmpty() ? true: false;
	}
	
}
