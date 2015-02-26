package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import controller.Chargement;
import controller.ControllerPoule;
import controller.ControllerTournoi;
import model.Equipe;
import model.Match;
import model.Poule;
import model.Sport;
import model.Tournoi;
import model.TournoiPoule;

/**
 * @author Manon Gaillardot et Willian Lanners
 *
 */
public class ConsoleView implements ViewInterface {

	private static Scanner sc = new Scanner(System.in);

	public ConsoleView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void afficherMenuPrincipal() {
		String choix = "";
		Pattern pattern = Pattern.compile("^[0-1]$");

		choix = "";
		System.out.println("\n\t*******************************");
		System.out.println("\t**  GESTIONNAIRE DE TOURNOI  **");
		System.out.println("\t*******************************\n");
		System.out.println(" 1: Créer un nouveau tournoi");
		System.out.println(" 0: Quitter le programme");
		choix = saisie(pattern, choix);
		switch (choix) {
		case "1":
			afficherSeparation();
			break;
		case "0":
			System.out.println("\nSortie du programme.");
			System.exit(0);
		}

	}

	// choix du type de tournoi
	public boolean choixOrgaTournoi() {
		String orga = "";
		Pattern pattern = Pattern.compile("^[0-1]$");
		System.out.println("- Organisation du tournoi:");
		System.out.println("0: tournoi à phase de poules/phase finale");
		System.out.println("1: tournoi par élimination directe");
		orga = saisie(pattern, orga);
		if (orga.equals("0")) {
			return false;
		} else {
			return true;
		}
	}

	// controle des saisies avec pattern
	public String saisie(Pattern pattern, String choix) {
		do {
			sc = new Scanner(System.in);
			System.out.print(" => ");
			choix = sc.nextLine();
		} while (!pattern.matcher(choix).find());
		return choix;
	}

	// affiche la creation d un tournoi
	public void creationTournoi(Tournoi tournoi) {
		tournoi.setNom(choixNomTournoi());
		tournoi.setSport(choixSportTournoi());
		tournoi.setNbrEquipes(choixNbrEquipe(tournoi.isTournoiPoules(),
				tournoi.getSport()));
	}

	public String choixNomTournoi() {
		System.out.println("\n- Nom du tournoi:");
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]*$");
		return saisie(pattern, "");
	}

	public Sport choixSportTournoi() {
		Sport sport = null;
		String sType = "";
		System.out.println("\n- Type de sport:");
		System.out.println("c: sport collectif");
		System.out.println("i: sport individuel");
		Pattern pattern = Pattern.compile("^[ci]$");
		sType = saisie(pattern, sType);
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

	public void afficherSportsIndivConsole() {
		int i = 0;
		for (Sport s : Chargement.getSportIndiv()) {
			System.out.println(i + ": " + s.getNom());
			i++;
		}
	}

	public void afficherSportsCoConsole() {
		int i = 0;
		for (Sport s : Chargement.getSportCo()) {
			System.out.println(i + ": " + s.getNom());
			i++;
		}
	}

	public static Sport selectionSport(List<Sport> listSport) {
		int index = -1;
		while ((index < 0) || (index > listSport.size() - 1)) {
			try {
				sc = new Scanner(System.in);
				System.out.print(" => ");
				index = sc.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("Format erroné.");
			}
		}
		return listSport.get(index);
	}

	public int choixNbrEquipe(boolean istournoiPoule, Sport sport) {
		int nbr = 0;
		String str = sport.getType() == 'c' ? "d'équipes" : "de joueurs";
		if (istournoiPoule) {
			String nbrPoules = "";
			Pattern pattern = Pattern.compile("^([2-9]|1[0-9]|20)$");
			System.out.println("\n- Nombre de poules de 4 - minimum 2:");
			nbrPoules = saisie(pattern, nbrPoules);
			nbr = Integer.parseInt(nbrPoules) * 4;
		} else {
			String nbrEq = "";
			Pattern pattern2 = Pattern.compile("^([2-9]|[1-7][0-9]|80)$");
			System.out.println("\n- Nombre " + str + " - minimum 2:");
			nbrEq = saisie(pattern2, nbrEq);
			nbr = Integer.parseInt(nbrEq);
		}
		return nbr;
	}

	public void afficherSeparation() {
		System.out
				.println("\n --------------------------------------------------\n");
	}

	public void afficherTour(Tournoi tournoi) {
		int numTour = tournoi.getNumTourActuel();
		int indiceTour = numTour + 1;
		System.out.println("\n TOUR " + indiceTour);
		System.out.println(" *******");
		for (int i = 0; i < tournoi.getTour().size(); i++) {
			afficherMatch(tournoi.getTour().get(i));
		}

		for (Match m : tournoi.getTour()) {
			saisieScoreMatch(m, false);
		}
	}

	public void afficherMatch(Match m) {
		System.out.println(" >> " + m.toString());
	}

	public void saisieScoreMatch(Match m, boolean matchPoule) {
		int s1 = -1, s2 = -1;
		// si match elim directe, match nul possible
		if (matchPoule) {
			System.out.println("\n - Saisir le score du match '"
					+ m.getEquipe1().getNom() + " vs "
					+ m.getEquipe2().getNom() + ":");
			System.out.print("Score '" + m.getEquipe1().getNom() + "' ");
			s1 = saisieScore(s1);
			System.out.print("Score '" + m.getEquipe2().getNom() + "' ");
			s2 = saisieScore(s2);
		} else { // sinon, possible
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

	public void modifierEquipes(Tournoi tournoi) {
		String choix;
		do {
			choix = "";
			afficherSeparation();
			System.out.println("\t==============================");
			System.out.println("\t|| 1: Afficher les équipes  ||");
			System.out.println("\t|| 2: Modifier les équipes  ||");
			System.out.println("\t|| 3: Lancer le tournoi     ||");
			System.out.println("\t==============================");
			Pattern pattern = Pattern.compile("^[1-3]$");
			choix = saisie(pattern, choix);

			if (choix.equals("1")) {
				afficherEquipes(tournoi);
			} else if (choix.equals("2")) {
				modifierEquipe(tournoi);
			}
		} while (!choix.equals("3"));
		alerteLancement(tournoi);
	}

	public void afficherEquipes(Tournoi tournoi) {
		ArrayList<Equipe> lst_equipes = tournoi.getListEquipes();
		for (Equipe e : lst_equipes) {
			System.out.println("Nom: " + e.getNom() + " - Nombre de joueurs: "
					+ e.getNbr_joueurs() + " - Description: "
					+ e.getDescription());
		}
	}

	public void modifierEquipe(Tournoi tournoi) {
		ArrayList<Equipe> lst = tournoi.getListEquipes();
		int i = 0, choix = -1;
		String choixModif = "";

		System.out.println("\nQuelle équipe souhaitez-vous modifier ?");
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

		System.out.println("\nModification de l'équipe '"
				+ lst.get(choix).getNom() + ":");
		System.out.println("1: Modifier le nom");
		System.out.println("2: Modifier la description");
		System.out.println("3: Modifier le nombre de joueurs");
		Pattern pattern = Pattern.compile("^[1-3]$");
		choixModif = saisie(pattern, choixModif);
		switch (choixModif) {
		case "1":
			modifNomEquipe(lst, choix);
			break;
		case "2":
			modifDescriEquipe(lst, choix);
			break;
		case "3":
			modifNbrEquipe(lst, choix);
			break;
		}
	}

	public void modifNomEquipe(ArrayList<Equipe> lst, int choixEqui) {
		System.out.print("Nouveau nom ");
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]*$");
		lst.get(choixEqui).setNom(saisie(pattern, ""));
	}

	public void modifNbrEquipe(ArrayList<Equipe> lst, int choixEqui) {
		System.out.print("Nombre de joueurs ");
		Pattern pattern = Pattern.compile("^([1-9]|[1-2][0-9])$");
		String nbrJoueurs = "";
		nbrJoueurs = saisie(pattern, nbrJoueurs);
		lst.get(choixEqui).setNbr_joueurs(Integer.parseInt(nbrJoueurs));
	}

	public void modifDescriEquipe(ArrayList<Equipe> lst, int choixEqui) {
		System.out.print("Nouvelle description ");
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]*$");
		String desc = "";
		desc = saisie(pattern, desc);
		lst.get(choixEqui).setDescription(desc);
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

	public void afficherAnnoncePhasePoules(TournoiPoule tournoi) {
		System.out
				.println("\n**************************************************");
		System.out
				.println("*PHASE DE POULES | Liste des poules et des matchs*");
		System.out
				.println("**************************************************");
		for (int numPoule = 0; numPoule < tournoi.getListPoules().size(); numPoule++) {
			System.out.println("\nPoule " + (numPoule) + ":");
			System.out.println("________");
			afficherEquipesPoule(tournoi.getListPoules().get(numPoule));
		}
	}

	public void afficherAnnoncePhaseFinale() {
		System.out.println("\n**************");
		System.out.println("*PHASE FINALE*");
		System.out.println("**************");
	}

	public void afficherMatchsPoule(Poule p) {
		for (Match m : p.getListMatchs()) {
			afficherMatch(m);
		}
	}

	public void afficherEquipesPoule(Poule p) {
		for (Equipe e : p.getListEquipes()) {
			System.out.print("- " + e.getNom() + " -");
		}
		System.out.print("\n");
	}

	public void afficherPoulesARemplir(TournoiPoule tournoi) {
		int i = 0;
		for (Poule p : tournoi.getListPoules()) {
			if (!p.isMatchsFinis()) {
				System.out.println(i + ": Poule " + i);
			}
			i++;
		}
	}

	public int choixRemplissagePoule(TournoiPoule tournoi) {
		int choix = -1;
		// afficher les poules a remplir
		System.out.println("\nCHOISIR UNE POULE:");
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
		return choix;
	}

	public void saisirScorePoule(Poule p, int choix) {
		System.out.println("\n* POULE " + choix + " *");
		afficherMatchsPoule(p);
		for (Match m : p.getListMatchs()) {
			saisieScoreMatch(m, true);
		}

	}

	public void afficherVainqueurPoule(Poule p, int choix) {
		System.out.print("\nLes vainqueurs de la poule " + choix + " sont: ");
		for (int i = 0; i < p.getListVainqueurs().size(); i++) {
			Equipe e = p.getListVainqueurs().get(i);
			if (i == 0) {
				System.out.print(e.getNom());
			} else {
				System.out.print(" et " + e.getNom());
			}
		}
		System.out.print("\n");
	}

	public void annonceVainqueurTournoi(Tournoi tournoi) {
		System.out.println("\n/!\\ VAINQUEUR DU TOURNOI: "
				+ tournoi.getTour().get(0).getVainqueur().getNom() + " /!\\\n");
	}

	public void afficherStatistiques(ArrayList<Equipe> meilAtta,
			ArrayList<Equipe> meilDef) {
		System.out.println("\n\t----------------");
		System.out.println("\t| STATISTIQUES |");
		System.out.println("\t----------------");
		System.out.print("\n Meilleure attaque : ");
		for (Equipe e : meilAtta) {
			System.out.print("- " + e.getNom() + " -");
		}
		System.out.print("\n Meilleure défense : ");
		for (Equipe e : meilDef) {
			System.out.print("- " + e.getNom() + " -");
		}
		afficherSeparation();
	}

	public void deroulementElimDirecte(Tournoi tournoi,
			ControllerTournoi controller) {
		// on affiche un tour tant que ce n'est pas la fin du tournoi
		do {
			afficherTour(tournoi);
			controller.passeTourSuivantED(tournoi);
		} while (!controller.finTournoiED(tournoi));
		// annonce du vainqueur
		annonceVainqueurTournoi(tournoi);
		// affichage des stats
		afficherStatistiques(controller.statsMeilleureAttaque(tournoi),
				controller.statsMeilleureDefense(tournoi));
	}

	public void deroulementPoule(TournoiPoule tournoi,
			ControllerPoule controller) {
		// phase de poule
		afficherAnnoncePhasePoules(tournoi);
		// tant qu'une poule n a pas fini ses matchs
		while (!controller.passePhaseFinale(tournoi)) {
			int choix = choixRemplissagePoule(tournoi);
			Poule p = tournoi.getListPoules().get(choix);
			saisirScorePoule(p, choix);
			// obtenir goal averages et points matchs
			controller.finMatchsPoule(p);
			afficherVainqueurPoule(p, choix);
		}

		// phase finale
		afficherAnnoncePhaseFinale();
		controller.lancementPhaseFinale(tournoi);
		do {
			afficherTour(tournoi);
			controller.passeTourSuivantED(tournoi);
		} while (!controller.finTournoiED(tournoi));

		annonceVainqueurTournoi(tournoi);
		afficherStatistiques(controller.statsMeilleureAttaque(tournoi),
				controller.statsMeilleureDefense(tournoi));
	}

}
