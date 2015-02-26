package model;

/**
 * @author Manon Gaillardot et Willian Lanners
 *
 */
public class Joueur {

	// attributs
	private String nom;
	private String prenom;
	private int age;

	public Joueur() {
		super();
		// TODO Auto-generated constructor stub
	}

	// constructeur
	public Joueur(Equipe equ, int num) {
		// valeurs par defaut
		this.nom = equ.getNom() + "_Player" + num;
		this.prenom = "Bob";
		this.age = 20;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
