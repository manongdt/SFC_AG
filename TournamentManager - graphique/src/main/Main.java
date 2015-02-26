package main;

import java.util.Scanner;
import java.util.regex.Pattern;

import model.TournoiElimDirecte;
import model.TournoiPoule;
import view.ConsoleView;
import view.SwingView;
import controller.ControllerElimDirecte;
import controller.ControllerPoule;
import controller.ControllerTournoi;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Pattern pattern = Pattern.compile("^[SC]$");
		String sView = "";
		do {
			System.out
					.print("Choisissez le mode console ou graphique (C ou S): ");
			sView = sc.nextLine();
		} while (!pattern.matcher(sView).find());

		switch (sView) {
		case "C":
			ConsoleView cv = new ConsoleView();
			boolean nouveau = true;
			do {
				cv.afficherMenuPrincipal();
				boolean isTournoiED = cv.choixOrgaTournoi();
				if (isTournoiED) {
					ControllerElimDirecte cED = new ControllerElimDirecte(cv,
							null, sView);
					cED.start();
				} else {
					ControllerPoule cP = new ControllerPoule(cv, null, sView);
					cP.start();
				}
			} while (nouveau);
			break;
		case "S":
			new SwingView();
			break;

			//sc.close();
		}

	}
}
