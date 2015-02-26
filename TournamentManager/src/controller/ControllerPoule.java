package controller;

import java.util.ArrayList;
import java.util.Collections;

import view.ConsoleView;
import view.SwingView;
import view.ViewInterface;
import model.Equipe;
import model.Match;
import model.Poule;
import model.TournoiPoule;

public class ControllerPoule extends ControllerTournoi {

	private TournoiPoule tournoi;
	private ViewInterface view;

	public ControllerPoule() {
		// TODO Auto-generated constructor stub
	}

	public ControllerPoule(ConsoleView console, SwingView swing) {
		view = console == null ? swing : console;
		this.tournoi = new TournoiPoule();
	}

	public void start() {
		// saisie des attributs du tournoi
		view.creationTournoi(tournoi);
		// calcul du nbr de tours
		calculNbrToursED(tournoi);
		// creation des equipes
		creerEquipes(tournoi);
		// affichage du menu pour modifier les equipes
		view.modifierEquipes(tournoi);
		// initialisation du tournoi a poule
		lancementTournoiPoules(tournoi);
		// deroulement du tournoi
		view.deroulementPoule(tournoi, this);
	}

	public void lancementTournoiPoules(TournoiPoule tournoi) {
		// placement aleatoire des equipes dans la liste du premier tour
		Collections.shuffle(tournoi.getListEquipesTourActuel());
		// creation tours poules du tournoi
		creationMatchsPoules(tournoi);
	}

	public void creationMatchsPoules(TournoiPoule tournoi) {
		int nbrEquipesPoule = TournoiPoule.NBR_EQUIPES_POULE;
		ArrayList<Equipe> listEq = tournoi.getListEquipesTourActuel();
		ArrayList<Poule> listPoule = tournoi.getListPoules();
		ArrayList<Match> listMatchs = new ArrayList<Match>();
		ArrayList<Equipe> listEqPoule = new ArrayList<Equipe>();

		int indice = 0;
		int IDpoule = 1;
		while ((indice + (nbrEquipesPoule - 1)) < listEq.size()) {
			for (int i = 0; i < 4; i++) {
				for (int j = i + 1; j < 4; j++) {
					listMatchs.add(new Match(listEq.get(indice + i), listEq
							.get(indice + j)));
				}
				listEqPoule.add(listEq.get(indice + i));
			}
			listPoule.add(new Poule(listEqPoule, listMatchs, IDpoule));
			listEqPoule.clear();
			listMatchs.clear();
			indice += 4;
			IDpoule++;
		}
	}

	public boolean passePhaseFinale(TournoiPoule tournoi) {
		for (Poule p : tournoi.getListPoules()) {
			if (!p.isMatchsFinis()) {
				return false;
			}
		}
		for (Poule p : tournoi.getListPoules()) {
			tournoi.addListEquiGagnantes(p.getListVainqueurs());
		}
		return true;
	}

	public void finMatchsPoule(Poule p) {
		for (Match m : p.getListMatchs()) {
			m.calculPointsGoalAvMatchs();
			m.calculButsTotaux();
		}
		Collections.sort(p.getListEquipes());
		Collections.reverse(p.getListEquipes());
		for (int i = 0; i < p.getListEquipes().size() && i < 2; i++) {
			p.getListVainqueurs().add(p.getListEquipes().get(i));
		}
	}

	public void lancementPhaseFinale(TournoiPoule tournoi) {
		tournoi.getListEquipesTourActuel().clear();
		tournoi.getListEquipesTourActuel().addAll(
				tournoi.getListEquiGagnantes());
		tournoi.getListEquiGagnantes().clear();
		// placement aleatoire des equipes dans la liste
		Collections.shuffle(tournoi.getListEquipesTourActuel());
		creationMatchsED(tournoi);
	}
}
