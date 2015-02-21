package controller;

import java.util.ArrayList;
import java.util.Collections;

import view.AbstractView;
import view.ConsoleView;
import model.ComparatorMeilleureAttaque;
import model.ComparatorMeilleureDefense;
//import view.SwingView;
import model.Equipe;
import model.Match;
import model.Sport;
import model.Tournoi;

public class ControllerTournoi {

	protected String sView;
	protected ConsoleView viewConsole;

	public ControllerTournoi(String sView) {
		super();
		this.sView = sView;
	}

	public void start(String sView) {
		switch (sView) {
		case "C":
			this.viewConsole = new ConsoleView(this);

			this.viewConsole.afficherMenuPrincipal();

			break;
		case "S":
			// this.viewMode = new SwingView();
			break;
		default:
			// this.viewMode = new SwingView();
			break;

		}

	}

	public void creerTournoi(boolean isTournoiPoules, String nomTournoi,
			Sport sport, int nbrEq) {
		if (isTournoiPoules) {
			ControllerPoules cP = new ControllerPoules(sView, viewConsole,
					nomTournoi, sport, nbrEq);
			cP.start();
		} else {
			ControllerElimDirecte cED = new ControllerElimDirecte(sView,
					viewConsole, nomTournoi, sport, nbrEq);
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

	public boolean passeTourSuivantED(Tournoi tournoi) {
		int numTour = tournoi.getNumTourActuel();
		ArrayList<Match> tour = tournoi.getTour();
		int i = 0;
		while (i < tour.size()) {
			if (tour.get(i).getVainqueur() == null) {
				return false;
			}
			i++;
		}
		tournoi.setNumTourActuel(numTour + 1);
		for (Match m : tour) {
			tournoi.getListEquiGagnantes().add(m.getVainqueur());
		}
		creationMatchsED(tournoi);
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
