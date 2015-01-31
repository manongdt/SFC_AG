package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.Sport;
import service.Chargement;

public class Console {

	private static Scanner sc = new Scanner(System.in);;

	// Menu - creation d'un tournoi en mode console
	public static void menuConsole() {

		afficherMenu();
		String nom_tournoi = choixNomTournoi();
		Sport sport = choixSportTournoi();
		int orga = choixOrgaTournoi();

		if (orga == 1) {
			System.out.println("\nVotre tournoi '" + nom_tournoi
					+ "' est un tournoi à phase de poules/phase finale. Le sport choisi est: "+sport.getNom());
		} else {
			System.out.println("\nVotre tournoi '" + nom_tournoi
					+ "' est un tournoi à élimination directe. Le sport choisi est: "+sport.getNom());
		}

		System.out.println("");
	}

	public static void afficherMenu() {
		System.out.println("***************************");
		System.out.println("**Gestionnaire de tournoi**");
		System.out.println("***************************\n");
		System.out.println("CREER UN NOUVEAU TOURNOI");
		afficherSeparation();
	}

	public static String choixNomTournoi() {
		// TODO Regex
		System.out.print("- Saisie du nom du tournoi\n => ");
		String nom_tournoi = sc.nextLine();
		return nom_tournoi;
	}

	public static Sport choixSportTournoi() {
		Sport sport = null;
		// sport co ou indiv
		int type = typeSport();
		// chargement de la liste des sports
		Chargement.chargerSport();
		System.out.println("\n- Choisissez un sport parmi ceux proposés: ");
		if (type == 1) {
			afficherSportsCoConsole();
			sport = selectionSport(Chargement.getSport_co());
		} else {
			afficherSportsIndivConsole();
			sport = selectionSport(Chargement.getSport_indiv());
		}
		return sport;
	}

	public static int typeSport() {
		int type = 0;
		System.out.println("\n- Tournoi collectif: taper '1'");
		System.out.println("- Tournoi individuel: taper '2'");
		// TODO Regex
		while ((type != 1) && (type != 2)) {
			try {
				System.out.print(" => ");
				sc = new Scanner(System.in);
				type = sc.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("Format erroné.");
			}
		}
		return type;
	}

	public static Sport selectionSport(List<Sport> listSport) {
		int index = 0;
		// TODO Regex
		while ((index < 1) || (index > listSport.size())) {
			try {
				System.out.print(" => ");
				sc = new Scanner(System.in);
				index = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Format erroné.");
			}
		}

		return listSport.get(index-1);
	}

	public static int choixOrgaTournoi() {
		int orga = 0;
		System.out.println("\n- Phase de poules/phase finale: taper '1'");
		System.out.println("- Elimination directe: taper '2'");

		// TODO Regex
		while ((orga != 1) && (orga != 2)) {
			try {
				System.out.print(" => ");
				sc = new Scanner(System.in);
				orga = sc.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("Format erroné.");
			}
		}
		return orga;
	}
	
	public static void choixNombreEquipe(){
		
	}

	public static void afficherSportsIndivConsole() {
		int i = 1;
		for (Sport s : Chargement.getSport_indiv()) {
			System.out.println(i + ": " + s.getNom());
			i++;
		}
	}

	public static void afficherSportsCoConsole() {
		int i = 1;
		for (Sport s : Chargement.getSport_co()) {
			System.out.println(i + ": " + s.getNom());
			i++;
		}
	}

	public static void afficherSeparation() {
		System.out.println(" _________________________");
	}
}
