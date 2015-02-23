/**
 * 
 */
package model;

import java.util.Comparator;

/**
 * @author Manon Gaillardot
 *
 */
public class ComparatorMeilleureAttaque implements Comparator<Equipe> {

	/**
	 * 
	 */
	public ComparatorMeilleureAttaque() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Equipe o1, Equipe o2) {
		int i = o1.getTotalButs() > o2.getTotalButs() ? 1
				: o1.getTotalButs() < o2.getTotalButs() ? -1 : 0;
		return i;
		
	}

}
