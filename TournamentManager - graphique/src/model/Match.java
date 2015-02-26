package model;

public class Match {
	// attributs
	private Equipe equipe1;
	private Equipe equipe2;
	private int score1;
	private int score2;

	public Match() {
		super();
		// TODO Auto-generated constructor stub
	}

	// constructeur
	public Match(Equipe e1, Equipe e2) {
		equipe1 = e1;
		equipe2 = e2;
		score1 = -1;
		score2 = -1;
	}

	public void setScore(int score1, int score2) {
		this.score1 = score1;
		this.score2 = score2;
	}

	public Equipe getVainqueur() {
		if (score1 != -1 && score2 != -1) {
			equipe1.addTotalButs(score1);
			equipe2.addTotalButs(score2);
			equipe1.addTotalButsEncaisse(score2);
			equipe2.addTotalButsEncaisse(score1);
			if (score1 > score2) {
				return equipe1;
			} else if (score1 < score2) {
				return equipe2;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public Equipe getEquipe1() {
		return equipe1;
	}

	public void setEquipe1(Equipe equipe1) {
		this.equipe1 = equipe1;
	}

	public Equipe getEquipe2() {
		return equipe2;
	}

	public void setEquipe2(Equipe equipe2) {
		this.equipe2 = equipe2;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}
	
	public String toString(){
		return equipe1.getNom() + " vs "+ equipe2.getNom();
	}

}
