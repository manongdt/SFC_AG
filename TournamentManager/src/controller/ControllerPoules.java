package controller;

import java.util.ArrayList;

import view.AbstractView;
import view.ConsoleView;
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
		int numTourActuel = tournoi.getNumTourActuel();
		int nbrToursTotal = tournoi.getNbrTours();
		lancementTournoiPoules(tournoi);
		while (numTourActuel < nbrToursTotal) {
			// si c'est la phase de poule
			if (numTourActuel == 0) {

			} else { // si c'est la phase finale
				this.viewMode.afficherTour(tournoi);
			}
		}
	}

	public void creationToursPoules(TournoiPoules tournoi) {
		int nbrEquipesPoule = TournoiPoules.getNbrEquipesPoule();
		ArrayList<Equipe> listEq = tournoi.getListEquipesTourActuel();
		ArrayList<Poule> listPoule = tournoi.getListPoules();
		ArrayList<Match> listMatchs = new ArrayList<Match>();
		ArrayList<Equipe> listEqPoule = new ArrayList<Equipe>();

		int indice = 0;
		while ((indice < listEq.size())
				|| ((indice + nbrEquipesPoule) < listEq.size())) {
			for (int i = 0; i < 4; i++) {
				for (int j = i + 1; j < 4; j++) {
					listMatchs.add(new Match(listEq.get(i), listEq.get(j)));
				}
				listEqPoule.add(listEq.get(indice + i));
			}
			listPoule.add(new Poule(listEqPoule, listMatchs));
			listEqPoule.clear();
			listMatchs.clear();
			indice += 4;
		}
	}

	public void lancementTournoiPoules(TournoiPoules tournoi) {
		this.viewMode.alerteLancement();
		// placement aleatoire des equipes dans la liste
		ControllerTournoi.shuffleList(tournoi.getListEquipes());
		// creation tours poules du tournoi
		creationToursPoules(tournoi);
	}
}
