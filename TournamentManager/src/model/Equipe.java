package model;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
	// attributs
	private int nbrJoueurs;
	private Sport sport;
	private String nom;
	private String description;
	private int id;
	private int points;
	private int goalAverage;
	private List<Joueur> list_joueurs = new ArrayList<Joueur>();
	private List<Integer> list_scores;

	public Equipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	// constructeur equipe
	public Equipe(Sport s, int numEquipe) {
		this.sport = s;
		this.id = numEquipe;
		// valeurs par defaut
		this.nbrJoueurs = sport.getNbr_joueurs();
		this.nom = "Equipe" + numEquipe;
		this.goalAverage = 0;
		this.description = "Aucune description de l'équipe disponible pour le moment.";
		for (int i = 0; i < nbrJoueurs; i++) {
			list_joueurs.add(new Joueur(this, i));
		}
		this.list_scores = new ArrayList<Integer>();
	}

	public List<Integer> getList_scores() {
		return list_scores;
	}

	public void setList_scores(List<Integer> list_scores) {
		this.list_scores = list_scores;
	}

	public int getNbr_joueurs() {
		return nbrJoueurs;
	}

	public void setNbr_joueurs(int nbr_joueurs) {
		this.nbrJoueurs = nbr_joueurs;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Joueur> getList_joueurs() {
		return list_joueurs;
	}

	public void setList_joueurs(List<Joueur> list_joueurs) {
		this.list_joueurs = list_joueurs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbrJoueurs() {
		return nbrJoueurs;
	}

	public void setNbrJoueurs(int nbrJoueurs) {
		this.nbrJoueurs = nbrJoueurs;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getGoalAverage() {
		return goalAverage;
	}

	public void setGoalAverage(int goalAverage) {
		this.goalAverage = goalAverage;
	}
	
}
