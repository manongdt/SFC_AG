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

/**
 * @author Manon Gaillardot et Willian Lanners
 *
 */
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
		// creation matchs de poule du tournoi
		creationMatchsPoules(tournoi);
	}
	
	// preparation de la phase finale
	public void lancementPhaseFinale(TournoiPoule tournoi) {
		tournoi.getListEquipesTourActuel().clear();
		tournoi.getListEquipesTourActuel().addAll(
				tournoi.getListEquiGagnantes());
		tournoi.getListEquiGagnantes().clear();
		// placement aleatoire des equipes dans la liste
		Collections.shuffle(tournoi.getListEquipesTourActuel());
		//creation matchs elim directe
		creationMatchsED(tournoi);
	}

	// creation des matchs en phase de poule
	public void creationMatchsPoules(TournoiPoule tournoi) {
		int nbrEquipesPoule = TournoiPoule.NBR_EQUIPES_POULE;
		ArrayList<Equipe> listEq = tournoi.getListEquipesTourActuel();
		ArrayList<Poule> listPoule = tournoi.getListPoules();
		ArrayList<Match> listMatchs = new ArrayList<Match>();
		ArrayList<Equipe> listEqPoule = new ArrayList<Equipe>();

		int indice = 0;
		// ID de la poule
		int IDpoule = 1;
		// tant qu'on ne depasse pas la taille de la liste, on prend 4 elements
		while ((indice + (nbrEquipesPoule - 1)) < listEq.size()) {
			// on commence par prendre le premier element de la liste
			for (int i = 0; i < 4; i++) {
				// et l'element suivant
				for (int j = i + 1; j < 4; j++) {
					// et on cree un match entre eux
					listMatchs.add(new Match(listEq.get(indice + i), listEq
							.get(indice + j)));
				}
				// on ajoute l'element a une liste d'equipes
				listEqPoule.add(listEq.get(indice + i));
			}
			// on cree une nouvelle poule avec les matchs et les equipes
			listPoule.add(new Poule(listEqPoule, listMatchs, IDpoule));
			// et on recommence
			listEqPoule.clear();
			listMatchs.clear();
			indice += 4;
			IDpoule++;
		}
	}

	// retourne true si on peut passer en phase finale,
	// ajoute les vainqueurs a la liste des gagnants pour phase finale
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

	// a appeler quand les matchs d'une poule sont finis
	// calcul les points, goal averages et buts totaux
	// prend les 2 vainqueurs de la poule
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
}
