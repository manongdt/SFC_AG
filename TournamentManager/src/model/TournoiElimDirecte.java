package model;

/**
 * @author Manon Gaillardot et Willian Lanners
 *
 */
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
