/**
 * 
 */
package view;

import model.Match;
import model.Poule;
import model.Sport;
import model.Tournoi;
import model.TournoiPoules;

/**
 * @author Manon Gaillardot
 *
 */
public abstract class AbstractView {

	public abstract void afficherMenuPrincipal();
	public abstract String choixNomTournoi();
	public abstract Sport choixSportTournoi();
	public abstract int choixOrgaTournoi();
	public abstract int choixNbrEquipe(int i, Sport s);
	public abstract void saisieScoreMatch(Match m, boolean b);
	public abstract int choixSousMenu();
	public abstract void modifierEquipe(Tournoi tournoi);
	public abstract void alerteLancement(Tournoi tournoi);
	public abstract void afficherTour(Tournoi tournoi);
	public abstract void annonceVainqueur(Tournoi tournoi);
	public abstract void afficherAnnoncePhasePoules(TournoiPoules tournoi);
	public abstract void afficherAnnoncePhaseFinale();
	public abstract int saisieScore(int s);
	public abstract void saisirScorePoule(TournoiPoules tournoi);
	public abstract void afficherPoule(Poule p);

//	public abstract
//	public abstract
//	public abstract
}