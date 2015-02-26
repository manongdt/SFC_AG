/**
 * 
 */
package view;

import controller.ControllerPoule;
import controller.ControllerTournoi;
import model.Tournoi;
import model.TournoiPoule;

/**
 * @author Manon Gaillardot
 *
 */
public interface ViewInterface {
	
	public void creationTournoi(Tournoi tournoi);
	
	public void modifierEquipes(Tournoi tournoi);
	
	public void deroulementElimDirecte(Tournoi tournoi, ControllerTournoi controller);
	
	public void deroulementPoule(TournoiPoule tournoi, ControllerPoule controller);

}
