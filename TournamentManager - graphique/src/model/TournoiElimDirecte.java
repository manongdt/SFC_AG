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
		this.numTourActuel = 0;
		isTournoiPoules = false;
		this.nbrTours = ControllerElimDirecte.calculNbrToursED(nbrEquipes);
		ControllerTournoi.creerEquipes(this);
		this.listEquipesTourActuel.addAll(listEquipes);
	}	

}
