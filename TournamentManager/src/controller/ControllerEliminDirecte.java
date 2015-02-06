package controller;

import java.util.ArrayList;

import model.Equipe;
import model.Match;
import model.TournoiElimiDirecte;

public class ControllerEliminDirecte extends ControllerTournoi {

	public static int calculNbrTours(int nbrEq) {
		int nbr_equ = 1;
		int puiss2 = 0;
		int exposant = 0;
		while (nbr_equ > 0) {
			nbr_equ = nbrEq;
			exposant++;
			puiss2 = (int) Math.pow(2, exposant);
			nbr_equ = nbr_equ - puiss2;
		}
		return exposant;
	}

	public static void creationToursTournoi(TournoiElimiDirecte tournoi) {
		int nbrEq = tournoi.getNbrEquipes();
		int nbrTours = tournoi.getNbrTours();
		ArrayList<Match[]> list_tours = tournoi.getListTours();
		
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

	public static void creationMatchs(TournoiElimiDirecte tournoi) {
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
				tour[indice / 2] = new Match(
						listEq.get(indice), listEq.get(indice + 1));
				indice += 2;
			} else {
				listEqTourGagn.add(listEq.get(indice));
				indice += 2;
			}
		}
	}

	public static void lancementTournoiElimin(TournoiElimiDirecte tournoi) {
		// placement aleatoire des equipes dans la liste
		ControllerTournoi.shuffleList(tournoi.getListEquipesTourActuel());
		// creation des tours du tournoi
		creationToursTournoi(tournoi);
	}

}
