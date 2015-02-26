package controller;

import java.util.ArrayList;
import java.util.Collections;

import model.ComparatorMeilleureAttaque;
import model.ComparatorMeilleureDefense;

import model.Equipe;
import model.Match;
import model.Sport;
import model.Tournoi;

/**
 * @author Manon Gaillardot et Willian Lanners
 *
 */
public class ControllerTournoi {

	// creation des equipes d'un tournoi
	public void creerEquipes(Tournoi tournoi) {
		int nbEq = tournoi.getNbrEquipes();
		Sport sport = tournoi.getSport();
		ArrayList<Equipe> listEq = tournoi.getListEquipes();
		for (int i = 0; i < nbEq; i++) {
			listEq.add(new Equipe(sport, i));
		}
		tournoi.setListEquipesTourActuel(listEq);
	}

	// calcul du nombre de tour pour la phase d'elimin directe
	public void calculNbrToursED(Tournoi tournoi) {
		int nbrEquipes;
		// si tournoi poule, on a 2 fois moins d equipes en phase finale
		if (tournoi.isTournoiPoules()) {
			nbrEquipes = tournoi.getNbrEquipes() / 2;
		} else {
			nbrEquipes = tournoi.getNbrEquipes();
		}
		int nbrEq2 = 1;
		int puiss = 0;
		int exposant = 0;
		// on regarde la puissance de 2 la plus grande
		// qui nous donne le nombre de tour
		while (nbrEq2 > 0) {
			nbrEq2 = nbrEquipes;
			exposant++;
			puiss = (int) Math.pow(2, exposant);
			nbrEq2 = nbrEq2 - puiss;
		}
		tournoi.setNbrTours(exposant);
	}

	// creation des matchs pour les elimin directes
	public void creationMatchsED(Tournoi tournoi) {
		ArrayList<Match> tour = tournoi.getTour();
		tour.clear();
		ArrayList<Equipe> listEq = tournoi.getListEquipesTourActuel();
		ArrayList<Equipe> listEqTourGagn = tournoi.getListEquiGagnantes();
		int indice = 0;
		// on ajoute les gagnants au tour actuel
		// gere aussi si nombre impair
		if (!listEqTourGagn.isEmpty()) {
			listEq.clear();
			listEq.addAll(listEqTourGagn);
			listEqTourGagn.clear();
		}
		//on cree des matchs entre deux equipes
		while ((indice < listEq.size()) || (indice + 1 < listEq.size())) {
			//si nombre pair
			if ((indice < listEq.size()) && (indice + 1 < listEq.size())) {
				tour.add(new Match(listEq.get(indice), listEq.get(indice + 1)));
				indice += 2;
			} else { // si equipe restante a la fin
				listEqTourGagn.add(listEq.get(indice));
				indice += 2;
			}
		}
		//on ajoute a la liste des tours
		tournoi.getListTours().add(tour);
		tournoi.setTour(tour);
	}

	// a la fin d'un tour on ajoute les vainqueurs a la liste
	// calcul des buts totaux et passe tour suivant
	// si on a pas fini le tournoi on cree des matchs
	public void passeTourSuivantED(Tournoi tournoi) {
		int numTour = tournoi.getNumTourActuel();
		ArrayList<Match> tour = tournoi.getTour();
		for (Match m : tour) {
			tournoi.getListEquiGagnantes().add(m.getVainqueur());
			m.calculButsTotaux();
		}
		tournoi.setNumTourActuel(numTour + 1);
		if (tournoi.getNumTourActuel() < tournoi.getNbrTours()) {
			creationMatchsED(tournoi);
		}
	}

	// retourne vrai si on a fini le tournoi
	public boolean finTournoiED(Tournoi tournoi) {
		if (tournoi.getNumTourActuel() < tournoi.getNbrTours()) {
			return false;
		}
		return true;
	}

	//retourne 2 ou 3 meilleures attaques
	public ArrayList<Equipe> statsMeilleureAttaque(Tournoi tournoi) {
		ArrayList<Equipe> meilAtta = new ArrayList<Equipe>();
		ComparatorMeilleureAttaque c1 = new ComparatorMeilleureAttaque();
		Collections.sort(tournoi.getListEquipes(), c1);
		Collections.reverse(tournoi.getListEquipes());
		for (int i = 0; i < tournoi.getListEquipes().size() && i < 3; i++) {
			meilAtta.add(tournoi.getListEquipes().get(i));
		}
		return meilAtta;

	}

	//retourne 2 ou 3 meilleures defenses
	public ArrayList<Equipe> statsMeilleureDefense(Tournoi tournoi) {
		ArrayList<Equipe> meilDef = new ArrayList<Equipe>();
		ComparatorMeilleureDefense c1 = new ComparatorMeilleureDefense();
		Collections.sort(tournoi.getListEquipes(), c1);
		Collections.reverse(tournoi.getListEquipes());
		for (int i = 0; i < tournoi.getListEquipes().size() && i < 3; i++) {
			meilDef.add(tournoi.getListEquipes().get(i));
		}
		return meilDef;
	}
}
