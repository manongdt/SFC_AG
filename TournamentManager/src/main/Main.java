package main;

import java.util.Scanner;
import java.util.regex.Pattern;

import view.ConsoleView;
import view.SwingView;
import controller.ControllerElimDirecte;
import controller.ControllerPoule;


public class Main {

	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		Pattern pattern = Pattern.compile("^[SC]$");
		String sView = "";
		// affiche en mode console choix de vue du programme
		do {
			System.out
					.print("Choisissez le mode console ou graphique (C ou S): ");
			sView = sc.nextLine();
		} while (!pattern.matcher(sView).find());
		// en fonction de la vue
		switch (sView) {
		case "C": // mode console
			ConsoleView cv = new ConsoleView();
			boolean nouveau = true;
			do {// on affiche le menu principal
				cv.afficherMenuPrincipal();
				// demande quel type de tournoi on lance
				boolean isTournoiED = cv.choixOrgaTournoi();
				if (isTournoiED) {
					ControllerElimDirecte cED = new ControllerElimDirecte(cv,
							null);
					cED.start();
				} else {
					ControllerPoule cP = new ControllerPoule(cv, null);
					cP.start();
				}
			} while (nouveau);
			break;
		case "S": // mode graphique
			// on lance la frame
			new SwingView();
			break;

		}

	}
}
