package model;

import java.util.ArrayList;

import controller.ControllerElimDirecte;
import controller.ControllerTournoi;

public class TournoiPoules extends TournoiElimDirecte {

	private static final int NBR_EQUIPES_POULE = 4;
	private int nbrPoules;
	private ArrayList<Poule> listPoules = new ArrayList<Poule>();

	public TournoiPoules() {
		super();
	}

	public TournoiPoules(String n, Sport s, int n_eq) {
		super(n, s, n_eq);
		this.nbrTours = ControllerElimDirecte.calculNbrTours(nbrEquipes / 2) + 1;
		this.nbrPoules = this.nbrEquipes / 4;
		ControllerTournoi.creerEquipes(nbrEquipes, sport, listEquipes);

	}

	public int getNbrPoules() {
		return nbrPoules;
	}

	public void setNbrPoules(int nbrPoules) {
		this.nbrPoules = nbrPoules;
	}

	public ArrayList<Poule> getListPoules() {
		return listPoules;
	}

	public void setListPoules(ArrayList<Poule> listPoules) {
		this.listPoules = listPoules;
	}

	public static int getNbrEquipesPoule() {
		return NBR_EQUIPES_POULE;
	}

}
