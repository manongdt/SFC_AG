package model;

import java.util.ArrayList;

/**
 * @author Manon Gaillardot et Willian Lanners
 *
 */
public class TournoiPoule extends Tournoi {

	public static final int NBR_EQUIPES_POULE = 4;
	public static final int NBR_MATCHS_POULE = 6;
	private ArrayList<Poule> listPoules = new ArrayList<Poule>();

	//constructeur tournoi a phase de poule
	public TournoiPoule() {
		super();
		this.isTournoiPoules = true;
	}

	public ArrayList<Poule> getListPoules() {
		return listPoules;
	}

	public void setListPoules(ArrayList<Poule> listPoules) {
		this.listPoules = listPoules;
	}

	public void addListEquiGagnantes(ArrayList<Equipe> listGagn) {
		listEquiGagnantes.addAll(listGagn);
	}

}
