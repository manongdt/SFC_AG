package model;

import controller.ControllerElimDirecte;
import controller.ControllerTournoi;

public class TournoiElimDirecte extends Tournoi {


	// constructeur pour tournoi a elimination directe
	public TournoiElimDirecte() {
		this.nom = "non defini";
		this.sport = null;
		this.nbrEquipes = 0;
		this.numTourActuel = 0;
		this.nbrTours = 0;
		isTournoiPoules = false;
	}	

}
