/**
 * 
 */
package view;

import controller.ControllerPoule;
import controller.ControllerTournoi;
import model.Tournoi;
import model.TournoiPoule;

/**
 * @author Manon Gaillardot et Willian Lanners
 *
 */

/**
 * Interface qui gere les deux vues
 *
 */
public interface ViewInterface {
	
	public void creationTournoi(Tournoi tournoi);
	
	public void modifierEquipes(Tournoi tournoi);
	
	public void deroulementElimDirecte(Tournoi tournoi, ControllerTournoi controller);
	
	public void deroulementPoule(TournoiPoule tournoi, ControllerPoule controller);

}
