package model;

import java.util.ArrayList;

import controller.ControllerPoules;

public class TournoiPoules extends TournoiElimDirecte {

	private static final int NBR_EQUIPES_POULE = 4;
	private static final int NBR_MATCHS_POULE = 6;
	private int nbrPoules;
	private ArrayList<Poule> listPoules = new ArrayList<Poule>();

	public TournoiPoules() {
		super();
	}

	public TournoiPoules(String n, Sport s, int n_eq) {
		super(n, s, n_eq);
		this.nbrPoules = this.nbrEquipes / 4;
		this.isTournoiPoules = true;
		this.nbrTours = ControllerPoules.calculNbrToursED(nbrEquipes/2);
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

	public static int getNbrMatchsPoule() {
		return NBR_MATCHS_POULE;
	}
	
	public void addListEquiGagnantes(ArrayList<Equipe> listGagn){
		listEquiGagnantes.addAll(listGagn);
	}

}
