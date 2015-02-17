/**
 * 
 */
package model;

import java.util.ArrayList;

public class Poule {

	private ArrayList<Match> listMatchs = new ArrayList<Match>();
	private ArrayList<Equipe> listEquipes = new ArrayList<Equipe>();
	private ArrayList<Equipe> listVainqueurs = new ArrayList<Equipe>();
	private boolean matchsFinis;

	public Poule() {
		super();
	}

	public Poule(ArrayList<Equipe> lstEq, ArrayList<Match> lstMatch) {
		this.listEquipes.addAll(lstEq);
		this.listMatchs.addAll(lstMatch);
		this.matchsFinis = false;
	}

	public ArrayList<Match> getListMatchs() {
		return listMatchs;
	}

	public void setListMatchs(ArrayList<Match> listMatchs) {
		this.listMatchs = listMatchs;
	}

	public ArrayList<Equipe> getListEquipes() {
		return listEquipes;
	}

	public void setListEquipes(ArrayList<Equipe> listEquipes) {
		this.listEquipes = listEquipes;
	}

	public ArrayList<Equipe> getListVainqueurs() {
		return listVainqueurs;
	}

	public void setListVainqueurs(ArrayList<Equipe> listVainqueurs) {
		this.listVainqueurs = listVainqueurs;
	}

	public boolean isMatchsFinis() {
		return matchsFinis;
	}

	public void setMatchsFinis(boolean matchsFinis) {
		this.matchsFinis = matchsFinis;
	}

}
