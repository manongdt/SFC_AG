package model;

public class Equipe {
	//attributs
	private int nbr_joueurs;
	private Sport sport;
	private String nom;
	private String description;
	
	
	//constructeur
	public Equipe(Sport s){
		sport = s;
		nbr_joueurs = sport.getNbr_joueurs();
	}
}
