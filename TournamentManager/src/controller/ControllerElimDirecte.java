package controller;

import java.util.Collections;

import view.ConsoleView;
import view.SwingView;
import view.ViewInterface;
import model.TournoiElimDirecte;

/**
 * @author Manon Gaillardot et Willian Lanners
 *
 */

public class ControllerElimDirecte extends ControllerTournoi {

	private TournoiElimDirecte tournoi;
	private ViewInterface view;

	public ControllerElimDirecte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ControllerElimDirecte(ConsoleView console, SwingView swing) {
		view = console == null ? swing : console;
		this.tournoi = new TournoiElimDirecte();
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
		// initialisation du tournoi a elimination directe
		initTournoiEliminDirecte(tournoi);
		// deroulement du tournoi
		view.deroulementElimDirecte(tournoi, this);
	}

	public void initTournoiEliminDirecte(TournoiElimDirecte tournoi) {
		// placement aleatoire des equipes dans la liste
		Collections.shuffle(tournoi.getListEquipesTourActuel());
		//creation des matchs du premier tour
		creationMatchsED(tournoi);
	}

}
