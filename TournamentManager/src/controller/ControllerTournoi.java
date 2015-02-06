package controller;

import java.util.Collections;
import java.util.List;

import model.Equipe;
import model.Match;
import model.Sport;
import model.TournoiElimiDirecte;

public class ControllerTournoi {

	public static void creerEquipes(int nbEq, Sport s, List<Equipe> lst) {
		for (int i = 0; i < nbEq; i++) {
			lst.add(new Equipe(s, i));
		}
	}

	public static void shuffleList(List<Equipe> lst) {
		Collections.shuffle(lst);
	}

	public static boolean passeTourSuivant(TournoiElimiDirecte tournoi) {
		int numTour = tournoi.getNumTourActuel();
		Match[] tour = tournoi.getListTours().get(numTour);
		int i = 0;
		while(i < tour.length){
			if(tour[i].getVainqueur() == null){
				return false;
			}
			i++;
		}
		tournoi.setNumTourActuel(tournoi.getNumTourActuel()+1);
		for(Match m : tour){
			tournoi.getListEquiGagnantes().add(m.getVainqueur());
		}
		return true;
	}
	
}
