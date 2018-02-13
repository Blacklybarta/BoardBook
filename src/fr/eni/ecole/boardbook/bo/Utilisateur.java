package fr.eni.ecole.boardbook.bo;

public class Utilisateur {
	private int id;
	private String nom;
	private String prenom;
	private String identifiant;
	private String mdp;
	private boolean conducteur;
	private boolean administrateur;
	
	public Utilisateur() {
		super();
	}
	

	public Utilisateur(String nom, String prenom, String identifiant, String mdp, boolean conducteur,
			boolean administrateur) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setIdentifiant (identifiant);
		this.setMdp(mdp);
		this.setConducteur(conducteur);
		this.setAdministrateur (administrateur);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getIdentifiant() {
		return identifiant;
	}


	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	public boolean isConducteur() {
		return conducteur;
	}


	public void setConducteur(boolean conducteur) {
		this.conducteur = conducteur;
	}


	public boolean isAdministrateur() {
		return administrateur;
	}


	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}


	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", identifiant=" + identifiant
				+ ", mdp=" + mdp + ", conducteur=" + conducteur + ", administrateur=" + administrateur + "]";
	}
	
	
	
	
	

}
