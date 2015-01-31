package model;

public class Sport {

	// attributs
	private String nom;
	private int nbr_joueurs;
	private int score_mini;
	private int score_max;

	// constructeur
	public Sport(String n, int nb_j, int smini, int smax) {
		this.nom = n;
		this.nbr_joueurs = nb_j;
		this.score_mini = smini;
		this.score_max = smax;
	}

	// getters et setters
	public int getScore_mini() {
		return score_mini;
	}

	public int getScore_max() {
		return score_max;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbr_joueurs() {
		return nbr_joueurs;
	}

	public void setNbr_joueurs(int nbr_joueurs) {
		this.nbr_joueurs = nbr_joueurs;
	}

	public String stringSport() {
		return "Sport[nom: " + nom + ", nb joueur(s): "
				+ nbr_joueurs + ", score mini et maxi: " + score_mini + "-"
				+ score_max+"]";
	}
}
