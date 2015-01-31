package model;

public abstract class Tournoi {
	
	//attributs
	private String nom;
	private Sport sport;
	private int nbr_equipes; 
	
	//constructeur
	public Tournoi (String n, Sport s, int n_eq){
		this.nom = n;
		this.sport = s;
		this.nbr_equipes = n_eq;
	}
	
	

}
