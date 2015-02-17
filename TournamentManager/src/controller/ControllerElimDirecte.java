package controller;

import java.util.ArrayList;
import java.util.Collections;

import view.AbstractView;
import model.Match;
import model.TournoiElimDirecte;

public class ControllerElimDirecte extends ControllerTournoi {

	private AbstractView viewMode;
	private TournoiElimDirecte tournoi;

	public ControllerElimDirecte(AbstractView viewM,
			TournoiElimDirecte tournoiED) {
		super();
		this.tournoi = tournoiED;
		this.viewMode = viewM;

	}

	public void start() {
		lancementTournoiEliminDirecte(tournoi);
		while (tournoi.getNumTourActuel() < tournoi.getNbrTours()) {
			// creation des matchs
			creationMatchsED(tournoi);
			this.viewMode.afficherTour(tournoi);
			ArrayList <Match> tour = tournoi.getTour();

			while (!this.passeTourSuivantED(tournoi)) {
				for (Match m : tour) {
					if (m.getVainqueur() == null) {
						this.viewMode.saisieScoreMatch(m, false);
					}
				}
			}
		}
		this.viewMode.annonceVainqueurTournoi(tournoi);
	}

	public void lancementTournoiEliminDirecte(TournoiElimDirecte tournoi) {
		viewMode.alerteLancement(tournoi);
		// placement aleatoire des equipes dans la liste
		Collections.shuffle(tournoi.getListEquipesTourActuel());
	}

}
