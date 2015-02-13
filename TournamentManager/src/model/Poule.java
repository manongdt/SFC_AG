/**
 * 
 */
package model;

import java.util.ArrayList;

public class Poule {

	private ArrayList<Match> listMatchs = new ArrayList<Match>();
	private ArrayList<Equipe> listEquipes = new ArrayList<Equipe>();
	private ArrayList<Equipe> listVainqueurs = new ArrayList<Equipe>();

	public Poule() {
		super();
	}

	public Poule(ArrayList<Equipe> lstEq, ArrayList<Match> lstMatch) {
		this.listEquipes = lstEq;
		this.listMatchs = lstMatch;
	}

}
