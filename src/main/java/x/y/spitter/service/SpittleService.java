package x.y.spitter.service;

import java.util.List;

import x.y.spitter.domain.Spitter;
import x.y.spitter.domain.Spittle;
import x.y.spitter.util.Tuple;

public interface SpittleService {

	void addSpittle(Spittle spittle, Spitter spitter);
	List<Tuple> getRecentSpittles(Spitter spitterMe);
	void deleteSpittle(long id);
}
