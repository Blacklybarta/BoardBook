package fr.eni.ecole.boardbook.bo;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.boardbook.bo.exception.ListException;
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;

public class Utilisateur {
	private int id;
	private String nom;
	private String prenom;
	private String identifiant;
	private String mdp;
	private boolean conducteur;
	private boolean administrateur;
	private List<Fiche> fiches = new ArrayList<Fiche>();
	
	public Utilisateur() {
		super();
	}
	

	public Utilisateur(String nom, String prenom, String identifiant, String mdp, boolean conducteur, boolean administrateur) throws ListException{
		ListException listE =  new ListException ();
		boolean isException = false;
		try {
			this.setNom(nom);
		} catch (ParameterNullException e) {
			listE.addException(e.getMessage());	
			isException = true;			
		}
		try {
			this.setPrenom(prenom);
		} catch (ParameterNullException e) {
			listE.addException(e.getMessage());
			isException = true;	
		}
		this.setIdentifiant (identifiant);
		try {
			this.setMdp(mdp);
		} catch (ParameterNullException e) {
			listE.addException(e.getMessage());
			isException = true;	
		}
		this.setConducteur(conducteur);
		this.setAdministrateur (administrateur);
		
		if (isException){
			throw listE;
		}
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


	public void setNom(String nom) throws ParameterNullException {
		if (nom != null && nom.trim().length() > 0){
			this.nom = nom;
		}else {
			throw new ParameterNullException("Le nom de l'utilisateur doit être renseigné");
		}		
	}
	


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) throws ParameterNullException {
		if (prenom != null && prenom.trim().length() > 0){
			this.prenom = prenom;
		}else {
			throw new ParameterNullException("Le prenom de l'utilisateur doit être renseigné");
		}		
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


	public void setMdp(String mdp) throws ParameterNullException {
		if (mdp != null && mdp.trim().length() > 0){
			this.mdp = mdp;
		}else {
			throw new ParameterNullException("Le mot de passe de l'utilisateur doit être renseigné");
		}		
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

	
	public List<Fiche> getFiches() {
		return fiches;
	}

	private void setFiches(List<Fiche> fiches) {
		this.fiches = fiches;
	}


	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", identifiant=" + identifiant
				+ ", mdp=" + mdp + ", conducteur=" + conducteur + ", administrateur=" + administrateur + "]";
	}
	
	
	
	
	

}
