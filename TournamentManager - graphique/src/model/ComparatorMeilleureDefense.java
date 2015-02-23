/**
 * 
 */
package model;

import java.util.Comparator;

/**
 * @author Manon Gaillardot
 *
 */
public class ComparatorMeilleureDefense implements Comparator<Equipe>{

	/**
	 * 
	 */
	public ComparatorMeilleureDefense() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Equipe o1, Equipe o2) {
		int i = o1.getTotalButsEncaisse() < o2.getTotalButsEncaisse() ? 1
				: o1.getTotalButsEncaisse() > o2.getTotalButsEncaisse() ? -1 : 0;
		return i;
	}

}
