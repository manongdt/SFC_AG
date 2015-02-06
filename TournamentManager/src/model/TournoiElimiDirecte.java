package model;

import java.util.ArrayList;

import controller.ControllerEliminDirecte;
import controller.ControllerTournoi;

public class TournoiElimiDirecte extends Tournoi {

	private ArrayList<Match[]> listTours = new ArrayList<Match[]>();
	private ArrayList<Equipe> listEquiTourActuel = new ArrayList<Equipe>();

	public TournoiElimiDirecte(String n, Sport s, int n_eq) {
		nom = n;
		sport = s;
		nbrEquipes = n_eq;
		nbrTours = ControllerEliminDirecte.calculNbrTours(nbrEquipes);
		numTourActuel = 0;
		ControllerTournoi.creerEquipes(nbrEquipes, sport, listEquipes);
		listEquiTourActuel.addAll(listEquipes);
	}


	public void setListTours(ArrayList<Match[]> listTours) {
		this.listTours = listTours;
	}


	public ArrayList<Match[]> getListTours() {
		return listTours;
	}

	public ArrayList<Equipe> getListEquipesTourActuel() {
		return listEquiTourActuel;
	}

	public void setListEquipesTourActuel(ArrayList<Equipe> lstEquTourActu) {
		this.listEquiTourActuel = lstEquTourActu;
	}



}
