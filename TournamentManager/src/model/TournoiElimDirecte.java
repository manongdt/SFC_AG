package model;

import controller.ControllerElimDirecte;
import controller.ControllerTournoi;

public class TournoiElimDirecte extends Tournoi {

	public TournoiElimDirecte() {
		super();
		// TODO Auto-generated constructor stub
	}

	// constructeur pour tournoi a elimination directe
	public TournoiElimDirecte(String n, Sport s, int n_eq) {
		this.nom = n;
		this.sport = s;
		this.nbrEquipes = n_eq;
		this.nbrTours = ControllerElimDirecte.calculNbrTours(nbrEquipes);
		this.numTourActuel = 0;
		ControllerTournoi.creerEquipes(nbrEquipes, sport, listEquipes);
		this.listEquiTourActuel.addAll(listEquipes);
	}	

}
