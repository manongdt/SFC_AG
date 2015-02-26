package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JLabel;

import view.ConsoleView;
import view.SwingView;
import model.ComparatorMeilleureAttaque;
import model.ComparatorMeilleureDefense;
//import view.SwingView;
import model.Equipe;
import model.Match;
import model.Sport;
import model.Tournoi;
import model.TournoiElimDirecte;
import model.TournoiPoule;

public class ControllerTournoi {

	public void creerEquipes(Tournoi tournoi) {
		int nbEq = tournoi.getNbrEquipes();
		Sport sport = tournoi.getSport();
		ArrayList<Equipe> listEq = tournoi.getListEquipes();
		for (int i = 0; i < nbEq; i++) {
			listEq.add(new Equipe(sport, i));
		}
		tournoi.setListEquipesTourActuel(listEq);
	}

	public void calculNbrToursED(Tournoi tournoi) {
		int nbrEquipes;
		if (tournoi.isTournoiPoules()) {
			nbrEquipes = tournoi.getNbrEquipes() / 2;
		} else {
			nbrEquipes = tournoi.getNbrEquipes();
		}
		int nbrEq2 = 1;
		int puiss2 = 0;
		int exposant = 0;
		while (nbrEq2 > 0) {
			nbrEq2 = nbrEquipes;
			exposant++;
			puiss2 = (int) Math.pow(2, exposant);
			nbrEq2 = nbrEq2 - puiss2;
		}
		tournoi.setNbrTours(exposant);
	}

	public void creationMatchsED(Tournoi tournoi) {
		ArrayList<Match> tour = tournoi.getTour();
		tour.clear();
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
				tour.add(new Match(listEq.get(indice), listEq.get(indice + 1)));
				indice += 2;
			} else {
				listEqTourGagn.add(listEq.get(indice));
				indice += 2;
			}
		}
		tournoi.getListTours().add(tour);
		tournoi.setTour(tour);
	}

	public void passeTourSuivantED(Tournoi tournoi) {
		int numTour = tournoi.getNumTourActuel();
		ArrayList<Match> tour = tournoi.getTour();
		for (Match m : tour) {
			tournoi.getListEquiGagnantes().add(m.getVainqueur());
			m.calculButsTotaux();
		}
		tournoi.setNumTourActuel(numTour + 1);
		if(tournoi.getNumTourActuel() < tournoi.getNbrTours()){
			creationMatchsED(tournoi);
		}
	}
	
	public boolean finTournoiED(Tournoi tournoi){
		if(tournoi.getNumTourActuel() < tournoi.getNbrTours()){
			return false;
		}
		return true;
	}

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
