package controller;

import java.util.ArrayList;
import java.util.Collections;

import view.ConsoleView;
import view.SwingView;
import model.Equipe;
import model.Match;
import model.Poule;
import model.Sport;
import model.TournoiElimDirecte;
import model.TournoiPoule;

public class ControllerPoule extends ControllerTournoi {

	private TournoiPoule tournoi;

	public ControllerPoule() {
		// TODO Auto-generated constructor stub
	}

	public ControllerPoule(ConsoleView console, SwingView swing, String sView) {
		this.viewConsole = console;
		this.viewSwing = swing;
		this.sView = sView;
		this.tournoi = new TournoiPoule();
	}

	public void start() {
		if (sView.equals("C")) {
			viewConsole.creationTournoi(tournoi);		
			calculNbrToursED(tournoi);
			creerEquipes(tournoi);
			this.viewConsole.modifierEquipes(tournoi);
			lancementTournoiPoules(tournoi);
			// phase de poule
			this.viewConsole.afficherAnnoncePhasePoules(tournoi);
			while (!this.passePhaseFinale(tournoi)) {
				int choix = this.viewConsole.choixRemplissagePoule(tournoi);
				Poule p = tournoi.getListPoules().get(choix);
				this.viewConsole.saisirScorePoule(p, choix);
				// pour avoir les goal average et les points des matchs
				for (Match m : p.getListMatchs()) {
					calculPointsGoalAvMatchs(m);
				}
				calculVainqueursPoule(p);
				this.viewConsole.afficherVainqueurPoule(p, choix);
				p.setMatchsFinis(true);
			}

			// phase finale
			this.viewConsole.afficherAnnoncePhaseFinale();
			lancementPhaseFinale(tournoi);
			do {
				viewConsole.afficherTour(tournoi);
				passeTourSuivantED(tournoi);
			} while (tournoi.getNumTourActuel() < tournoi.getNbrTours());

			this.viewConsole.annonceVainqueurTournoi(tournoi);
			ArrayList<Equipe> meilAtta = statsMeilleureAttaque(tournoi);
			ArrayList<Equipe> meilDef = statsMeilleureDefense(tournoi);
			this.viewConsole.afficherStatistiques(meilAtta, meilDef);
		}else{
			viewSwing.creationTournoi(tournoi);
			calculNbrToursED(tournoi);
			creerEquipes(tournoi);
			viewSwing.modifierEquipes(tournoi);
			lancementTournoiPoules(tournoi);
			viewSwing.deroulementPoule(tournoi, this);
			viewSwing.setVisible(true);
		}
	}

	public void lancementTournoiPoules(TournoiPoule tournoi) {
		// placement aleatoire des equipes dans la liste
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
		if (phasePouleFini(tournoi)) {
			for (Poule p : tournoi.getListPoules()) {
				tournoi.addListEquiGagnantes(p.getListVainqueurs());
			}
			return true;
		}
		return false;
	}

	public boolean phasePouleFini(TournoiPoule tournoi) {
		for (Poule p : tournoi.getListPoules()) {
			if (!p.isMatchsFinis()) {
				return false;
			}
		}
		return true;
	}

	public void calculVainqueursPoule(Poule p) {
		Collections.sort(p.getListEquipes());
		Collections.reverse(p.getListEquipes());
		for (int i = 0; i < p.getListEquipes().size() && i < 2; i++) {
			p.getListVainqueurs().add(p.getListEquipes().get(i));
		}

	}

	public void calculPointsGoalAvMatchs(Match m) {
		if (m.getScore1() > m.getScore2()) {
			m.getEquipe1().addPoints(3);
			m.getEquipe2().addPoints(0);
			m.getEquipe1().addGoalAverage(m.getScore1() - m.getScore2());
			m.getEquipe2().addGoalAverage(m.getScore2() - m.getScore1());
		} else if (m.getScore1() < m.getScore2()) {
			m.getEquipe1().addPoints(0);
			m.getEquipe2().addPoints(3);
			m.getEquipe1().addGoalAverage(m.getScore1() - m.getScore2());
			m.getEquipe2().addGoalAverage(m.getScore2() - m.getScore1());
		} else {
			m.getEquipe1().addPoints(1);
			m.getEquipe2().addPoints(1);
			m.getEquipe1().addGoalAverage(m.getScore1() - m.getScore2());
			m.getEquipe2().addGoalAverage(m.getScore2() - m.getScore1());
		}
	}

	public void lancementPhaseFinale(TournoiPoule tournoi) {
		tournoi.getListEquipesTourActuel().clear();
		tournoi.getListEquipesTourActuel().addAll(
				tournoi.getListEquiGagnantes());
		tournoi.getListEquiGagnantes().clear();
		// placement aleatoire des equipes dans la liste
		Collections.shuffle(tournoi.getListEquipesTourActuel());
	}
}
