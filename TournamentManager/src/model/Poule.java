/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author Manon Gaillardot et Willian Lanners
 *
 */
public class Poule {

	// attributs
	private int IDPoule;
	private ArrayList<Match> listMatchs = new ArrayList<Match>();
	private ArrayList<Equipe> listEquipes = new ArrayList<Equipe>();
	private ArrayList<Equipe> listVainqueurs = new ArrayList<Equipe>();

	public Poule() {
		super();
	}

	// constructeur
	public Poule(ArrayList<Equipe> lstEq, ArrayList<Match> lstMatch, int IDPoule) {
		this.IDPoule = IDPoule;
		this.listEquipes.addAll(lstEq);
		this.listMatchs.addAll(lstMatch);
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

	public int getIDPoule() {
		return IDPoule;
	}

	public void setIDPoule(int iDPoule) {
		IDPoule = iDPoule;
	}

	@Override
	public String toString() {
		return "Poule " + IDPoule;
	}

	// verifie que les matchs de la poule sont termines
	public boolean isMatchsFinis() {
		for (Match m : listMatchs) {
			if (m.isMatchFini() == false) {
				return false;
			}
		}
		return true;
	}

}
