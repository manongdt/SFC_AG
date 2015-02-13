package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import controller.Chargement;
import model.Equipe;
import model.Match;
import model.Sport;
import model.Tournoi;

public class ConsoleView extends AbstractView {

	private static Scanner sc = new Scanner(System.in);

	public ConsoleView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void afficherMenuPrincipal() {
		System.out.println("***************************");
		System.out.println("**Gestionnaire de tournoi**");
		System.out.println("***************************\n");
		System.out.println("CREER UN NOUVEAU TOURNOI");
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

	public char choixTypeSport() {
		String type;
		System.out.println("\n- Tournoi collectif: taper 'c'");
		System.out.println("- Tournoi individuel: taper 'i'");

		Pattern pattern = Pattern.compile("^[ci]$");
		do {
			System.out.print(" => ");
			type = sc.nextLine();
		} while (!pattern.matcher(type).find());

		return type.charAt(0);
	}

	public Sport choixSportTournoi(char type) {
		Sport sport = null;
		// chargement de la liste des sports
		Chargement.chargerSport();
		System.out.println("\n- Choisissez un sport parmi ceux proposés: ");
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
		int orga = 0;
		System.out.println("\n- Phase de poules/phase finale: taper '1'");
		System.out.println("- Elimination directe: taper '2'");
		while ((orga != 1) && (orga != 2)) {
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

	public int choixNbrEquipe(int orgaTournoi, char typeSport) {
		int nbr = 0;
		String str = typeSport == 'c' ? "d'équipes" : "de joueurs";
		switch (orgaTournoi) {
		case 2:
			System.out.println("\n- Saisie du nombre " + str + " (minimum 2):");
			while (nbr < 2) {
				try {
					System.out.print(" => ");
					nbr = sc.nextInt();

				} catch (InputMismatchException e) {
					System.out.println("Format erroné.");
				}
			}
			break;

		case 1:
			int nbr_poules = 0;
			System.out
					.println("\n- Saisie du nombre de poules de 4 (minimum 2):");
			while (nbr_poules < 2) {
				try {
					System.out.print(" => ");
					nbr_poules = sc.nextInt();

				} catch (InputMismatchException e) {
					System.out.println("Format erroné.");
				}
			}
			nbr = nbr_poules * 4;
			System.out
					.println("Vous avez choisi " + nbr_poules
							+ " poules. Le nombre " + str + " est donc de "
							+ nbr + ".");
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
				.println("\n ________________________________________________");
	}

	public void afficherMatch(Match m) {
		System.out.println(m.getEquipe1().getNom() + " vs "
				+ m.getEquipe2().getNom());
	}

	public void afficherTour(Tournoi tournoi) {
		int numTour = tournoi.getNumTourActuel();
		int indiceTour = numTour + 1;
		System.out.println("\n\tTour " + indiceTour);
		for (int i = 0; i < tournoi.getListTours().get(numTour).length; i++) {
			afficherMatch(tournoi.getListTours().get(numTour)[i]);
		}
	}

	public void saisieScoreMatch(Match m) {
		int s1 = -1, s2 = -1;
		System.out.println("Saisie du score pour le match '"
				+ m.getEquipe1().getNom() + " vs " + m.getEquipe2().getNom()
				+ "' (match nul impossible):");
		while (s1 == s2) {
			s1 = -1;
			s2 = -1;
			while (s1 < 0) {
				try {
					System.out.print("Score de '" + m.getEquipe1().getNom()
							+ "' => ");
					sc = new Scanner(System.in);
					s1 = sc.nextInt();

				} catch (InputMismatchException e) {
					System.out.println("Format erroné.");
				}
			}

			while (s2 < 0) {
				try {
					System.out.print("Score de '" + m.getEquipe2().getNom()
							+ "' => ");
					sc = new Scanner(System.in);
					s2 = sc.nextInt();

				} catch (InputMismatchException e) {
					System.out.println("Format erroné.");
				}
			}
		}
		m.setScore(s1, s2);
	}

	public int choixSousMenu() {
		int sm = 0;
		afficherSeparation();
		System.out.println("\nModifier les équipes: taper '1'");
		System.out.println("Lancer le tournoi: taper '2'");
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
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
		String nomEquipe;
		do {
			sc = new Scanner(System.in);
			nomEquipe = sc.nextLine();
		} while (!pattern.matcher(nomEquipe).find());
		lst.get(choix).setNom(nomEquipe);

	}

	public void alerteLancement() {
		afficherSeparation();
		System.out.println("\tLANCEMENT TOURNOI");
	}

	public void annonceVainqueur(Tournoi tournoi) {
		System.out.println("\n*** Vainqueur du tournoi: "
				+ tournoi.getListTours().get(tournoi.getNumTourActuel() - 1)[0]
						.getVainqueur().getNom() + " ***");
	}
}
