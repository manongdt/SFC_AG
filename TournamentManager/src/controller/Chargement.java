package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Sport;

/**
 * @author Manon Gaillardot et Willian Lanners
 *
 */
public class Chargement {

	private static String fichier_sports = "sports.txt";
	private static List<Sport> sport_co;
	private static List<Sport> sport_indiv;

	// permet de charger la liste des sports a partir d'un fichier
	public static void chargerSport() {

		try {// liste des sports collectifs
			sport_co = new ArrayList<Sport>();
			// liste des sports individuels
			sport_indiv = new ArrayList<Sport>();
			// on lit le fichier
			FileReader fr = new FileReader(fichier_sports);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			// on ajoute a la liste
			while (line != null) {
				String tab[] = line.split(",");
				Sport s = new Sport(tab[1], Integer.parseInt(tab[2]),
						Integer.parseInt(tab[3]), Integer.parseInt(tab[4]),
						tab[0].charAt(0));
				if (tab[0].equals("c")) {
					sport_co.add(s);
				} else if (tab[0].equals("i")) {
					sport_indiv.add(s);
				} else {
					sport_co.add(s);
					sport_indiv.add(s);
				}
				line = br.readLine();
			}
			// on ferme le buffer
			br.close();

		} catch (IOException ex) {
			System.out.println("Le fichier '" + fichier_sports
					+ "' n'a pas pu être lu.");
		}
	}

	public static List<Sport> getSportCo() {
		return sport_co;
	}

	public static List<Sport> getSportIndiv() {
		return sport_indiv;
	}

}
