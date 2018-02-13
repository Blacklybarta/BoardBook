package fr.eni.ecole.boardbook.bo;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.boardbook.bo.exception.ListException;
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;

public class Lieu {
	private int id;
	private String nom;
	private List<Fiche> fiches = new ArrayList<Fiche>();
	
	public Lieu (){
		
	}
	
	public Lieu(String nom) throws ListException{
		ListException listE =  new ListException ();
		boolean isException = false;
		try {
			this.setNom(nom);
		} catch (ParameterNullException e) {
			listE.addException(e.getMessage());
			isException = true;
		}
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
			throw new ParameterNullException("Le nom du lieu doit être renseigné");
		}		
	}

	public List<Fiche> getFiches() {
		return fiches;
	}

	private void setFiches(List<Fiche> fiches) {
		this.fiches = fiches;
	}
	
	
	@Override
	public String toString() {
		return "Lieu [id=" + id + ", nom=" + nom + "]";
	}
	
	

}
