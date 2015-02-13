package controller;

import java.util.Collections;
import java.util.List;

import view.AbstractView;
import view.ConsoleView;
import model.Equipe;
import model.Match;
import model.Sport;
import model.Tournoi;
import model.TournoiElimDirecte;
import model.TournoiPoules;

public class ControllerTournoi {

	private AbstractView viewMode;

	public ControllerTournoi() {
		super();
	}

	public void start(String sView) {
		switch (sView) {
		case "C":
			this.viewMode = new ConsoleView();
			break;
		case "S":
			break;
		default:
			break;

		}
		this.viewMode.afficherMenuPrincipal();
		String nom_tournoi = this.viewMode.choixNomTournoi();
		// sport co ou individuel pour proposition de sports
		char type = this.viewMode.choixTypeSport();
		Sport sport = this.viewMode.choixSportTournoi(type);
		int orga = this.viewMode.choixOrgaTournoi();
		int n_eq = this.viewMode.choixNbrEquipe(orga, type);

		if (orga == 1) {
			TournoiPoules tournoi = new TournoiPoules(nom_tournoi, sport, n_eq);
			sousMenu(tournoi);
			ControllerPoules cP = new ControllerPoules(viewMode, tournoi);
			cP.start();

		} else if (orga == 2) {
			TournoiElimDirecte tournoi = new TournoiElimDirecte(nom_tournoi,
					sport, n_eq);
			sousMenu(tournoi);
			ControllerElimDirecte cED = new ControllerElimDirecte(viewMode,
					tournoi);
			cED.start();
		}
	}

	public static void creerEquipes(int nbEq, Sport s, List<Equipe> lst) {
		for (int i = 0; i < nbEq; i++) {
			lst.add(new Equipe(s, i));
		}
	}

	public static void shuffleList(List<Equipe> lst) {
		Collections.shuffle(lst);
	}

	public static boolean passeTourSuivant(Tournoi tournoi) {
		int numTour = tournoi.getNumTourActuel();
		Match[] tour = tournoi.getListTours().get(numTour);
		int i = 0;
		while (i < tour.length) {
			if (tour[i].getVainqueur() == null) {
				return false;
			}
			i++;
		}
		tournoi.setNumTourActuel(tournoi.getNumTourActuel() + 1);
		for (Match m : tour) {
			tournoi.getListEquiGagnantes().add(m.getVainqueur());
		}
		return true;
	}

	public void sousMenu(Tournoi tournoi) {
		int choix;
		do {
			choix = this.viewMode.choixSousMenu();
			if (choix == 1) {
				this.viewMode.modifierEquipe(tournoi);
			}
		} while (choix == 1);
	}
}
