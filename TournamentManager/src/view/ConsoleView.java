package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import controller.Chargement;
import model.Equipe;
import model.Match;
import model.Poule;
import model.Sport;
import model.Tournoi;
import model.TournoiPoules;

public class ConsoleView extends AbstractView {

	private static Scanner sc = new Scanner(System.in);

	public ConsoleView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void afficherMenuPrincipal() {
		System.out.println("\n\t*******************************");
		System.out.println("\t**  GESTIONNAIRE DE TOURNOI  **");
		System.out.println("\t*******************************\n");
		System.out.println(" 1: Créer un nouveau tournoi");
		afficherSeparation();
	}

	public String choixNomTournoi() {
		System.out.println("- Nom du tournoi:");
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]*$");
		String nom_tournoi;
		do {
			System.out.print(" => ");
			nom_tournoi = sc.nextLine();
		} while (!pattern.matcher(nom_tournoi).find());
		return nom_tournoi;
	}

	public Sport choixSportTournoi() {
		Sport sport = null;
		String sType;
		System.out.println("\n- Type de sport:");
		System.out.println("c: sport collectif");
		System.out.println("i: sport individuel");
		Pattern pattern = Pattern.compile("^[ci]$");
		do {
			System.out.print(" => ");
			sType = sc.nextLine();
		} while (!pattern.matcher(sType).find());

		char type = sType.charAt(0);

		// chargement de la liste des sports
		Chargement.chargerSport();
		System.out.println("\n- Sport du tournoi: ");
		if (type == 'c') {
			afficherSportsCoConsole();
			sport = selectionSport(Chargement.getSportCo());
		} else {
			afficherSportsIndivConsole();
			sport = selectionSport(Chargement.getSportIndiv());
		}
		return sport;
	}

	public static Sport selectionSport(List<Sport> listSport) {
		int index = 0;
		while ((index < 1) || (index > listSport.size())) {
			try {
				sc = new Scanner(System.in);
				System.out.print(" => ");
				index = sc.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("Format erroné.");
			}
		}
		return listSport.get(index - 1);
	}

	public int choixOrgaTournoi() {
		int orga = -1;
		System.out.println("\n- Organisation du tournoi:");
		System.out.println("0: tournoi à phase de poules/phase finale");
		System.out.println("1: tournoi par élimination directe");
		while ((orga != 0) && (orga != 1)) {
			try {
				sc = new Scanner(System.in);
				System.out.print(" => ");
				orga = sc.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("Format erroné.");
			}
		}
		return orga;
	}

	public int choixNbrEquipe(int orgaTournoi, Sport sport) {
		int nbr = 0;
		String str = sport.getType() == 'c' ? "d'équipes" : "de joueurs";
		switch (orgaTournoi) {
		case 0:
			int nbr_poules = 0;
			System.out.println("\n- Nombre de poules de 4 - minimum 2:");
			while (nbr_poules < 2) {
				try {
					System.out.print(" => ");
					nbr_poules = sc.nextInt();

				} catch (InputMismatchException e) {
					System.out.println("Format erroné.");
				}
			}
			nbr = nbr_poules * 4;
			break;

		case 1:
			System.out.println("\n- Nombre " + str + " - minimum 2:");
			while (nbr < 2) {
				try {
					System.out.print(" => ");
					nbr = sc.nextInt();

				} catch (InputMismatchException e) {
					System.out.println("Format erroné.");
				}
			}
			break;
		}
		return nbr;
	}

	public void afficherSportsIndivConsole() {
		int i = 1;
		for (Sport s : Chargement.getSportIndiv()) {
			System.out.println(i + ": " + s.getNom());
			i++;
		}
	}

	public void afficherSportsCoConsole() {
		int i = 1;
		for (Sport s : Chargement.getSportCo()) {
			System.out.println(i + ": " + s.getNom());
			i++;
		}
	}

	public void afficherSeparation() {
		System.out
				.println("\n --------------------------------------------------\n");
	}

	public void afficherMatch(Match m) {
		System.out.println(" >> " + m.getEquipe1().getNom() + " vs "
				+ m.getEquipe2().getNom());
	}

	public void afficherTour(Tournoi tournoi) {
		int numTour = tournoi.getNumTourActuel();
		int indiceTour = numTour + 1;
		System.out.println("\n TOUR " + indiceTour);
		System.out.println(" *******");
		for (int i = 0; i < tournoi.getListTours().get(numTour).length; i++) {
			afficherMatch(tournoi.getListTours().get(numTour)[i]);
		}
	}

	public void saisieScoreMatch(Match m, boolean matchPoule) {
		int s1 = -1, s2 = -1;
		// si match de poule, match nul possible
		if (matchPoule) {
			System.out.println("\n - Saisir le score du match '"
					+ m.getEquipe1().getNom() + " vs "
					+ m.getEquipe2().getNom() + ":");
			System.out.print("Score '" + m.getEquipe1().getNom() + "' ");
			s1 = saisieScore(s1);
			System.out.print("Score '" + m.getEquipe2().getNom() + "' ");
			s2 = saisieScore(s2);
		} else {
			System.out.println("\n - Saisir le score du match '"
					+ m.getEquipe1().getNom() + " vs "
					+ m.getEquipe2().getNom() + "' (match nul impossible):");
			while (s1 == s2) {
				s1 = -1;
				s2 = -1;
				System.out.print(" Score '" + m.getEquipe1().getNom() + "' ");
				s1 = saisieScore(s1);
				System.out.print(" Score '" + m.getEquipe2().getNom() + "' ");
				s2 = saisieScore(s2);
			}
		}
		m.setScore(s1, s2);
	}

	public int saisieScore(int s) {
		s = -1;
		while (s < 0) {
			try {
				System.out.print("=> ");
				sc = new Scanner(System.in);
				s = sc.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("Format erroné.");
			}
		}
		return s;
	}

	public int choixSousMenu() {
		int sm = 0;
		afficherSeparation();
		System.out.println("\t==============================");
		System.out.println("\t|| 1: Modifier les équipes  ||");
		System.out.println("\t|| 2: Lancer le tournoi     ||");
		System.out.println("\t==============================");
		while ((sm != 1) && (sm != 2)) {
			try {
				sc = new Scanner(System.in);
				System.out.print(" => ");
				sm = sc.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("Format erroné.");
			}
		}
		return sm;
	}

	public void modifierEquipe(Tournoi tournoi) {
		ArrayList<Equipe> lst = tournoi.getListEquipes();
		System.out.println("\nQuelle équipe souhaitez-vous modifier ?");
		int i = 0, choix = -1;
		for (Equipe e : lst) {
			System.out.println(i + ": " + e.getNom());
			i++;
		}
		while ((choix < 0) || (choix > lst.size() - 1)) {
			try {
				sc = new Scanner(System.in);
				System.out.print(" => ");
				choix = sc.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("Format erroné.");
			}
		}

		System.out.println("Modification de l'équipe '"
				+ lst.get(choix).getNom() + ":");
		System.out.print("Nom:");
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]*$");
		String nomEquipe;
		do {
			sc = new Scanner(System.in);
			nomEquipe = sc.nextLine();
		} while (!pattern.matcher(nomEquipe).find());
		lst.get(choix).setNom(nomEquipe);

	}

	public void alerteLancement(Tournoi tournoi) {
		afficherSeparation();
		System.out.println("\t------------------------");
		System.out.println("\t| LANCEMENT DU TOURNOI |");
		System.out.println("\t------------------------");
		if (tournoi.isTournoiPoules()) {
			System.out.println("\n Tournoi '" + tournoi.getNom()
					+ "' à phase de poules/phase finale.");
			System.out
					.println("\n => Renseignez les scores pour chaque poule. "
							+ "\nUne fois une poule choisie, "
							+ "vous devez compléter tous ses matchs.\n");
		} else {
			System.out.println("\n Tournoi '" + tournoi.getNom()
					+ "' à élimination directe.");
		}
	}

	public void annonceVainqueur(Tournoi tournoi) {
		System.out.println("\n/!\\ VAINQUEUR DU TOURNOI: "
				+ tournoi.getListTours().get(tournoi.getNumTourActuel() - 1)[0]
						.getVainqueur().getNom() + " /!\\");
	}

	public void afficherAnnoncePhasePoules(TournoiPoules tournoi) {
		System.out
				.println("\n**************************************************");
		System.out
				.println("*PHASE DE POULES | Liste des poules et des matchs*");
		System.out
				.println("**************************************************");
		for (int numPoule = 0; numPoule < tournoi.getListPoules().size(); numPoule++) {
			System.out.println("\nPoule " + (numPoule + 1) + ":");
			System.out.println("________");
			afficherPoule(tournoi.getListPoules().get(numPoule));
		}
	}

	public void afficherAnnoncePhaseFinale() {
		System.out.println("\n**************");
		System.out.println("*PHASE FINALE*");
		System.out.println("**************");
	}

	public void afficherPoule(Poule p) {
		for (int numMatch = 0; numMatch < TournoiPoules.getNbrMatchsPoule(); numMatch++) {
			afficherMatch(p.getListMatchs().get(numMatch));
		}
	}

	public void afficherPoulesARemplir(TournoiPoules tournoi) {
		int i = 0;
		for (Poule p : tournoi.getListPoules()) {
			if (!p.isMatchsFinis()) {
				System.out.println(i + ": Poule " + i);
			}
			i++;
		}
	}

	public void saisirScorePoule(TournoiPoules tournoi) {
		int choix = -1;
		// afficher les poules a remplir
		System.out.println("\nChoisir une poule:");
		afficherPoulesARemplir(tournoi);
		while ((choix < 0) || (choix > (tournoi.getListPoules().size() - 1))) {
			try {
				sc = new Scanner(System.in);
				System.out.print(" => ");
				choix = sc.nextInt();
				if ((choix >= 0) && (choix < tournoi.getListPoules().size())) {
					if (tournoi.getListPoules().get(choix).isMatchsFinis()) {
						System.out
								.println("Scores renseignés pour cette poule. "
										+ "Veuillez en sélectionner une autre.");
						choix = -1;
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Format erroné.");
			}
		}
		Poule p = tournoi.getListPoules().get(choix);
		System.out.println("\n* POULE " + choix + " *");
		afficherPoule(p);
		for (Match m : p.getListMatchs()) {
			saisieScoreMatch(m, true);
			//pour avoir les goal average et les points des matchs
			m.getVainqueur();
		}
		p.setMatchsFinis(true);
	}
}
