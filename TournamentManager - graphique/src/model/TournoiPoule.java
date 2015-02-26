package model;

import java.util.ArrayList;

import controller.ControllerPoule;

public class TournoiPoule extends Tournoi {

	public static final int NBR_EQUIPES_POULE = 4;
	public static final int NBR_MATCHS_POULE = 6;
	private ArrayList<Poule> listPoules = new ArrayList<Poule>();

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
	
	public void addListEquiGagnantes(ArrayList<Equipe> listGagn){
		listEquiGagnantes.addAll(listGagn);
	}

}
