package model;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
	// attributs
	private int nbr_joueurs;
	private Sport sport;
	private String nom;
	private String description;
	private int id;
	private List<Joueur> list_joueurs = new ArrayList<Joueur>();
	private List<Integer> list_scores;

	// constructeur equipe
	public Equipe(Sport s, int numEquipe) {
		this.sport = s;
		this.id = numEquipe;
		// valeurs par defaut
		this.nbr_joueurs = sport.getNbr_joueurs();
		this.nom = "Equipe" + numEquipe;
		this.description = "Aucune description de l'équipe disponible pour le moment.";
		for (int i = 0; i < nbr_joueurs; i++) {
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
		return nbr_joueurs;
	}

	public void setNbr_joueurs(int nbr_joueurs) {
		this.nbr_joueurs = nbr_joueurs;
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
	
}
