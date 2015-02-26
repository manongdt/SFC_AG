package main;

import java.util.Scanner;
import java.util.regex.Pattern;

import model.TournoiElimDirecte;
import model.TournoiPoule;
import view.ConsoleView;
import view.SwingView;
import view.ViewInterface;
import controller.ControllerElimDirecte;
import controller.ControllerPoule;
import controller.ControllerTournoi;

public class Main {

	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
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
							null);
					cED.start();
				} else {
					ControllerPoule cP = new ControllerPoule(cv, null);
					cP.start();
				}
			} while (nouveau);
			break;
		case "S":
			new SwingView();
			break;

		}

	}
}
