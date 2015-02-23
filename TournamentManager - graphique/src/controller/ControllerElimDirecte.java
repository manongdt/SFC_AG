package controller;

import java.util.Collections;

import view.ConsoleView;
import model.Match;
import model.Sport;
import model.TournoiElimDirecte;

public class ControllerElimDirecte extends ControllerTournoi {

	private TournoiElimDirecte tournoi;

	public ControllerElimDirecte(String sView) {
		super(sView);
		// TODO Auto-generated constructor stub
	}

	public ControllerElimDirecte(String sView, ConsoleView viewConsole, String nomTournoi, Sport sport, int nbrEq) {
		super(sView);
		this.tournoi = new TournoiElimDirecte(nomTournoi, sport, nbrEq);
		this.viewConsole = viewConsole;
		this.sView = sView;
	}

	public void start() {
		//mode console
		if(sView.equals("C")){
			//affichage du menu modifier, afficher equipes ou lancer tournoi
			this.viewConsole.sousMenu(tournoi);
			this.viewConsole.alerteLancement(tournoi);
			lancementTournoiEliminDirecte(tournoi);
			while (tournoi.getNumTourActuel() < tournoi.getNbrTours()) {
				creationMatchsED(tournoi);
				this.viewConsole.afficherTour(tournoi);
				while (!this.passeTourSuivantED(tournoi)) {
					for (Match m : tournoi.getTour()) {
						if (m.getVainqueur() == null) {
							this.viewConsole.saisieScoreMatch(m, false);
						}
					}
				}
			}
			this.viewConsole.annonceVainqueurTournoi(tournoi);
			
		}else{ //mode graphique
			
		}
		
	}

	public void lancementTournoiEliminDirecte(TournoiElimDirecte tournoi) {
		// placement aleatoire des equipes dans la liste
		Collections.shuffle(tournoi.getListEquipesTourActuel());
		//creationMatchsED(tournoi);
	}

}
