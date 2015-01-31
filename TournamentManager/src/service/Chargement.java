package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Sport;

public class Chargement {

	private static String fichier_sports = "sports.txt";
	private static List<Sport> sport_co = new ArrayList<Sport>();
	private static List<Sport> sport_indiv = new ArrayList<Sport>();

	public static void chargerSport() {

		try {
			FileReader fr = new FileReader(fichier_sports);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();

			while (line != null) {
				String tab[] = line.split(",");
				Sport s = new Sport(tab[1], Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]));
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
			br.close();

		} catch (IOException ex) {
			System.out.println("Le fichier '" + fichier_sports
					+ "' n'a pas pu être lu.");
		}
	}

	public static List<Sport> getSport_co() {
		return sport_co;
	}

	public static List<Sport> getSport_indiv() {
		return sport_indiv;
	}



}
