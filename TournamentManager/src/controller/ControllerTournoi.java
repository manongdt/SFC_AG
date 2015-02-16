package controller;

import java.util.ArrayList;

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
		Sport sport = this.viewMode.choixSportTournoi();
		int orga = this.viewMode.choixOrgaTournoi();
		int n_eq = this.viewMode.choixNbrEquipe(orga, sport);

		if (orga == 0) {
			TournoiPoules tournoi = new TournoiPoules(nom_tournoi, sport, n_eq);
			sousMenu(tournoi);
			ControllerPoules cP = new ControllerPoules(viewMode, tournoi);
			cP.start();

		} else if (orga == 1) {
			TournoiElimDirecte tournoi = new TournoiElimDirecte(nom_tournoi,
					sport, n_eq);
			sousMenu(tournoi);
			ControllerElimDirecte cED = new ControllerElimDirecte(viewMode,
					tournoi);
			cED.start();
		}
	}

	public static void creerEquipes(Tournoi tournoi) {
		int nbEq = tournoi.getNbrEquipes();
		Sport sport = tournoi.getSport();
		ArrayList<Equipe> listEq = tournoi.getListEquipes();
		for (int i = 0; i < nbEq; i++) {
			listEq.add(new Equipe(sport, i));
		}
	}

	public static int calculNbrToursED(int nbrEq) {
		int nbrEq2 = 1;
		int puiss2 = 0;
		int exposant = 0;
		while (nbrEq2 > 0) {
			nbrEq2 = nbrEq;
			exposant++;
			puiss2 = (int) Math.pow(2, exposant);
			nbrEq2 = nbrEq2 - puiss2;
		}
		return exposant;
	}

	public void creationToursED(Tournoi tournoi) {
		int nbrEq = tournoi.getListEquipesTourActuel().size();
		int nbrTours = tournoi.getNbrTours();
		ArrayList<Match[]> list_tours = tournoi.getListTours();
		// nbr de matchs par tour
		int nbr_matchs_tours = (int) Math.ceil(nbrEq / 2);

		for (int l = 0; l < nbrTours; l++) {
			if (l != 0) {
				nbr_matchs_tours = (int) Math
						.ceil((double) nbr_matchs_tours / 2);
			}
			Match[] tour = new Match[nbr_matchs_tours];
			list_tours.add(tour);
		}
	}

	public void creationMatchsED(Tournoi tournoi) {
		int numTour = tournoi.getNumTourActuel();
		Match[] tour = tournoi.getListTours().get(numTour);
		ArrayList<Equipe> listEq = tournoi.getListEquipesTourActuel();
		ArrayList<Equipe> listEqTourGagn = tournoi.getListEquiGagnantes();
		int indice = 0;

		if (!listEqTourGagn.isEmpty()) {
			listEq.clear();
			listEq.addAll(listEqTourGagn);
			listEqTourGagn.clear();
		}
		while ((indice < listEq.size()) || (indice + 1 < listEq.size())) {
			if ((indice < listEq.size()) && (indice + 1 < listEq.size())) {
				tour[indice / 2] = new Match(listEq.get(indice),
						listEq.get(indice + 1));
				indice += 2;
			} else {
				listEqTourGagn.add(listEq.get(indice));
				indice += 2;
			}
		}
	}

	public boolean passeTourSuivantED(Tournoi tournoi) {
		int numTour = tournoi.getNumTourActuel();
		Match[] tour = tournoi.getListTours().get(numTour);
		int i = 0;
		while (i < tour.length) {
			if (tour[i].getVainqueur() == null) {
				return false;
			}
			i++;
		}
		tournoi.setNumTourActuel(numTour + 1);
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
