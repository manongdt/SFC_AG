package controller;

import java.util.ArrayList;
import java.util.Collections;

import view.ConsoleView;
import view.SwingView;
import model.Equipe;
import model.Match;
import model.Sport;
import model.Tournoi;
import model.TournoiElimDirecte;

public class ControllerElimDirecte extends ControllerTournoi {

	private TournoiElimDirecte tournoi;

	public ControllerElimDirecte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ControllerElimDirecte(ConsoleView console, SwingView swing,
			String sView) {
		this.viewConsole = console;
		this.viewSwing = swing;
		this.sView = sView;
		this.tournoi = new TournoiElimDirecte();
	}

	public void start() {
		// mode console
		if (sView.equals("C")) {
			//saisie des attributs du tournoi
			viewConsole.creationTournoi(tournoi);
			//calcul du nbr de tours
			calculNbrToursED(tournoi);
			//creation des equipes
			creerEquipes(tournoi);
			// affichage du menu modifier, afficher equipes ou lancer tournoi
			viewConsole.modifierEquipes(tournoi);
			lancementTournoiEliminDirecte(tournoi);
			do {
				viewConsole.afficherTour(tournoi);
				passeTourSuivantED(tournoi);
			} while (tournoi.getNumTourActuel() < tournoi.getNbrTours());

			this.viewConsole.annonceVainqueurTournoi(tournoi);
			ArrayList<Equipe> meilAtta = statsMeilleureAttaque(tournoi);
			ArrayList<Equipe> meilDef = statsMeilleureDefense(tournoi);
			this.viewConsole.afficherStatistiques(meilAtta, meilDef);

		} else { // mode graphique
			viewSwing.creationTournoi(tournoi);
			calculNbrToursED(tournoi);
			creerEquipes(tournoi);
			viewSwing.modifierEquipes(tournoi);
			lancementTournoiEliminDirecte(tournoi);
			viewSwing.deroulementElimDirecte(tournoi, this);
			viewSwing.setVisible(true);
		}
	}

	public void lancementTournoiEliminDirecte(TournoiElimDirecte tournoi) {
		// placement aleatoire des equipes dans la liste
		Collections.shuffle(tournoi.getListEquipesTourActuel());
		creationMatchsED(tournoi);
	}

}
