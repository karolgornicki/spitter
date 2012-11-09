package x.y.spitter.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import x.y.spitter.domain.Spitter;
import x.y.spitter.domain.Spittle;
import x.y.spitter.persistence.SpitterDao;
import x.y.spitter.util.Tuple;
import x.y.spitter.util.TupleComparator;

@Service("spittleServiceImpl")
public class SpittleServiceImpl implements SpittleService {

	private SpitterDao hibernateSpitterDao;
	
	@Autowired
	public SpittleServiceImpl(SpitterDao hibernateSpitterDao) {
		this.hibernateSpitterDao = hibernateSpitterDao;
	}
	
	@Override
	public void addSpittle(Spittle spittle, Spitter spitter) {
		
		spittle.setAuthor(spitter);
		spittle.setCreatedAt(Calendar.getInstance().getTime());
		
		hibernateSpitterDao.addSpittle(spittle);
	}

	@Override
	public List<Tuple> getRecentSpittles(Spitter spitterMe) {
		
		List<Tuple> recentSpittles = new ArrayList<>();
		
		//Spittles from users I follow. 
		for(Spitter spitter : spitterMe.getSpittersIFollow()) {
			for(Tuple tuple : hibernateSpitterDao.getRecentSpittles(spitter.getId())) {
				recentSpittles.add(tuple);
			}
		}
		
		//Spittles from myself.
		for(Tuple tuple : hibernateSpitterDao.getRecentSpittles(spitterMe.getId())) {
			recentSpittles.add(tuple);
		}
		
		Collections.sort(recentSpittles, new TupleComparator());
		
		return recentSpittles;
	}

	@Override
	public void deleteSpittle(long id) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		hibernateSpitterDao.deleteSpittle(username, id);		
	}

}
