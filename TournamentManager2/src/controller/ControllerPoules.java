package controller;

import java.util.ArrayList;
import java.util.Collections;

import view.ConsoleView;
import model.Equipe;
import model.Match;
import model.Poule;
import model.Sport;
import model.TournoiPoules;

public class ControllerPoules extends ControllerTournoi {

	private TournoiPoules tournoi;

	public ControllerPoules(String sView) {
		super(sView);
		// TODO Auto-generated constructor stub
	}

	public ControllerPoules(String sView, ConsoleView viewConsole, String nomTournoi, Sport sport, int nbrEq) {
		super(sView);
		this.tournoi = new TournoiPoules(nomTournoi, sport, nbrEq);
		this.viewConsole = viewConsole;
		this.sView = sView;
	}

	public void start() {
		if (sView.equals("C")) {
			this.viewConsole.sousMenu(tournoi);
			this.viewConsole.alerteLancement(tournoi);
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
			while (tournoi.getNumTourActuel() < tournoi.getNbrTours()) {
				
				this.viewConsole.afficherTour(tournoi);
				// tant qu'il manque des vainqueurs on continue
				while (!this.passeTourSuivantED(tournoi)) {
					for (Match m : tournoi.getTour()) {
						if (m.getVainqueur() == null) {
							this.viewConsole.saisieScoreMatch(m, false);
						}
					}
				}
			}
			this.viewConsole.annonceVainqueurTournoi(tournoi);
		}
	}

	public void lancementTournoiPoules(TournoiPoules tournoi) {
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
		if (phasePouleFini(tournoi)) {
			for (Poule p : tournoi.getListPoules()) {
				tournoi.addListEquiGagnantes(p.getListVainqueurs());
			}
			return true;
		}
		return false;
	}

	public boolean phasePouleFini(TournoiPoules tournoi) {
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

	public void lancementPhaseFinale(TournoiPoules tournoi) {
		tournoi.getListEquipesTourActuel().clear();
		tournoi.getListEquipesTourActuel().addAll(
				tournoi.getListEquiGagnantes());
		tournoi.getListEquiGagnantes().clear();
		// placement aleatoire des equipes dans la liste
		Collections.shuffle(tournoi.getListEquipesTourActuel());
		creationMatchsED(tournoi);
	}
}
