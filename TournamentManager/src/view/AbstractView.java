/**
 * 
 */
package view;

import model.Match;
import model.Sport;
import model.Tournoi;

/**
 * @author Manon Gaillardot
 *
 */
public abstract class AbstractView {

	public abstract void afficherMenuPrincipal();
	public abstract String choixNomTournoi();
	public abstract char choixTypeSport();
	public abstract Sport choixSportTournoi(char c);
	public abstract int choixOrgaTournoi();
	public abstract int choixNbrEquipe(int i, char c);
	public abstract void saisieScoreMatch(Match m);
	public abstract int choixSousMenu();
	public abstract void modifierEquipe(Tournoi tournoi);
	public abstract void alerteLancement();
	public abstract void afficherTour(Tournoi tournoi);
	public abstract void annonceVainqueur(Tournoi tournoi);
//	public abstract
//	public abstract
//	public abstract
//	public abstract
//	public abstract
//	public abstract
}
