package controller;

import java.util.ArrayList;
import java.util.Collections;

import view.AbstractView;
import model.Equipe;
import model.Match;
import model.Poule;
import model.TournoiPoules;

public class ControllerPoules extends ControllerTournoi {

	private AbstractView viewMode;
	private TournoiPoules tournoi;

	public ControllerPoules(AbstractView viewM, TournoiPoules tournoiP) {
		super();
		this.tournoi = tournoiP;
		this.viewMode = viewM;
	}

	public void start() {
		
		lancementTournoiPoules(tournoi);
		// phase de poule
		this.viewMode.afficherAnnoncePhasePoules(tournoi);
		while (!this.passePhaseFinale(tournoi)) {
			this.viewMode.saisirScorePoule(tournoi);
		}

		// phase finale
		this.viewMode.afficherAnnoncePhaseFinale();
		lancementPhaseFinale(tournoi);
		while (tournoi.getNumTourActuel() < tournoi.getNbrTours()) {
			creationMatchsED(tournoi);
			this.viewMode.afficherTour(tournoi);
			Match[] tour = tournoi.getListTours().get(
					tournoi.getNumTourActuel());
			while (!this.passeTourSuivantED(tournoi)) {
				for (Match m : tour) {
					if (m.getVainqueur() == null) {
						this.viewMode.saisieScoreMatch(m, false);
					}
				}
			}
		}
		this.viewMode.annonceVainqueur(tournoi);

	}

	public void lancementTournoiPoules(TournoiPoules tournoi) {
		this.viewMode.alerteLancement(tournoi);
		// placement aleatoire des equipes dans la liste
		Collections.shuffle(tournoi.getListEquipesTourActuel());
		// creation tours poules du tournoi
		creationMatchsPoules(tournoi);
	}

	public void creationMatchsPoules(TournoiPoules tournoi) {
		int nbrEquipesPoule = TournoiPoules.getNbrEquipesPoule();
		ArrayList<Equipe> listEq = tournoi.getListEquipesTourActuel();
		ArrayList<Poule> listPoule = tournoi.getListPoules();
		ArrayList<Match> listMatchs = new ArrayList<Match>();
		ArrayList<Equipe> listEqPoule = new ArrayList<Equipe>();

		int indice = 0;
		while ((indice + (nbrEquipesPoule - 1)) < listEq.size()) {
			for (int i = 0; i < 4; i++) {
				for (int j = i + 1; j < 4; j++) {
					listMatchs.add(new Match(listEq.get(indice + i), listEq
							.get(indice + j)));
				}
				listEqPoule.add(listEq.get(indice + i));
			}
			listPoule.add(new Poule(listEqPoule, listMatchs));
			listEqPoule.clear();
			listMatchs.clear();
			indice += 4;
		}
	}

	public boolean passePhaseFinale(TournoiPoules tournoi) {
		if (tournoi.isPhasePouleTerminee()) {
			for (Poule p : tournoi.getListPoules()) {
				tournoi.addListEquiGagnantes(p.getListVainqueurs());
			}
			return true;
		}
		return false;
	}

	public void lancementPhaseFinale(TournoiPoules tournoi) {
		tournoi.getListEquipesTourActuel().clear();
		tournoi.getListEquipesTourActuel().addAll(
				tournoi.getListEquiGagnantes());
		tournoi.getListEquiGagnantes().clear();
		// placement aleatoire des equipes dans la liste
		Collections.shuffle(tournoi.getListEquipesTourActuel());
		// creation des tours du tournoi
		creationToursED(tournoi);
	}
}
