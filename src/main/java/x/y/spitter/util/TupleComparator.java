package x.y.spitter.util;

import java.util.Comparator;

public class TupleComparator implements Comparator<Tuple> {

	/**
	 * Sort Tuples starting from the newest ones.
	 */
	@Override
	public int compare(Tuple arg0, Tuple arg1) {
		return arg1.getDate().compareTo(arg0.getDate());
	}

}
