package model;

import java.util.ArrayList;

public abstract class Tournoi {

	// attributs
	protected String nom;
	protected Sport sport;
	protected int nbrEquipes;
	protected int nbrTours;
	protected int numTourActuel;
	protected boolean isTournoiPoules;
	protected ArrayList<Equipe> listEquipes = new ArrayList<Equipe>();
	protected ArrayList<Equipe> listEquiGagnantes = new ArrayList<Equipe>();
	protected ArrayList<ArrayList <Match>> listTours = new ArrayList<ArrayList <Match>>();
	protected ArrayList<Match> tour = new ArrayList<Match>();
	protected ArrayList<Equipe> listEquipesTourActuel = new ArrayList<Equipe>();

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public int getNbrEquipes() {
		return nbrEquipes;
	}

	public void setNbrEquipes(int nbr_equipes) {
		this.nbrEquipes = nbr_equipes;
	}

	public ArrayList<Equipe> getListEquipes() {
		return listEquipes;
	}

	public void setListEquipes(ArrayList<Equipe> list_equipes) {
		this.listEquipes = list_equipes;
	}
	
	public ArrayList<Equipe> getListEquiGagnantes() {
		return listEquiGagnantes;
	}

	public void setListEquiGagnantes(ArrayList<Equipe> lstEquGagn) {
		this.listEquiGagnantes = lstEquGagn;
	}
	public int getNbrTours() {
		return nbrTours;
	}

	public void setNbrTours(int nbrTours) {
		this.nbrTours = nbrTours;
	}

	public int getNumTourActuel() {
		return numTourActuel;
	}

	public void setNumTourActuel(int tourActuel) {
		this.numTourActuel = tourActuel;
	}

	public void setListTours(ArrayList<ArrayList <Match>> listTours) {
		this.listTours = listTours;
	}

	public ArrayList<ArrayList <Match>> getListTours() {
		return listTours;
	}

	public ArrayList<Equipe> getListEquipesTourActuel() {
		return listEquipesTourActuel;
	}

	public void setListEquipesTourActuel(ArrayList<Equipe> lstEquTourActu) {
		this.listEquipesTourActuel = lstEquTourActu;
	}

	public boolean isTournoiPoules() {
		return isTournoiPoules;
	}

	public ArrayList<Match> getTour() {
		return tour;
	}

	public void setTour(ArrayList<Match> tour) {
		this.tour = tour;
	}

}
