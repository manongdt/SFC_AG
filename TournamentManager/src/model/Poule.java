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
		int nbrVainqueurs = 0;
		for (int i = 0; i < listEquipes.size() && nbrVainqueurs < 2; i++) {
			int nbrMeilleur = 0;

			for (int j = i + 1; j < listEquipes.size() && nbrMeilleur < 2; j++) {

				if (listEquipes.get(i).getPoints() < listEquipes.get(j)
						.getPoints()) {
					nbrMeilleur++;

				} else if (listEquipes.get(i).getPoints() == listEquipes.get(j)
						.getPoints()) {

					if (listEquipes.get(i).getGoalAverage() < listEquipes
							.get(j).getGoalAverage()) {
						nbrMeilleur++;
					}
				}
			}
			if (nbrMeilleur < 2) {
				listVainqueurs.add(listEquipes.get(i));
				nbrVainqueurs++;
			}
		}
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
