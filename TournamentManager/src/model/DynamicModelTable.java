/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.table.AbstractTableModel;

/**
 * @author Manon Gaillardot et Willian Lanners
 *
 */
public class DynamicModelTable extends AbstractTableModel {

	/**
	 * table modele pour le tableau en swing view dans la vue poule
	 */
	private static final long serialVersionUID = -148171706531619190L;
	private ArrayList<Match> matchs = new ArrayList<Match>();
	private final String[] entetes = { "Matchs", "Score equipe 1",
			"Score equipe 2" };

	public DynamicModelTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRowCount() {
		return matchs.size();
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return matchs.get(rowIndex).toString();
		case 1:
			if (matchs.get(rowIndex).getScore1() == -1) {
				return -1;
			}
			return matchs.get(rowIndex).getScore1();
		case 2:
			if (matchs.get(rowIndex).getScore2() == -1) {
				return -1;
			}
			return matchs.get(rowIndex).getScore2();
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void setMatchs(ArrayList<Match> listMatchs) {
		matchs = listMatchs;
		fireTableDataChanged();
	}

	public void clear() {
		for (int i = 0; i < matchs.size(); i++) {
			matchs.clear();
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return false;
		case 1:
			return true;
		case 2:
			return true;
		default:
			return false; // Ne devrait jamais arriver
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (aValue != null) {
			Match m = matchs.get(rowIndex);
			Pattern pattern = Pattern
					.compile("^([0-9]|[1-9][0-9]|1[0-9][0-9]|200)$");
			switch (columnIndex) {
			case 1:
				if (pattern.matcher((String) aValue).find()) {
					m.setScore1(Integer.parseInt((String) aValue));
				}
				break;
			case 2:
				if (pattern.matcher((String) aValue).find()) {
					m.setScore2(Integer.parseInt((String) aValue));
				}
				break;
			}
		}
	}

}
