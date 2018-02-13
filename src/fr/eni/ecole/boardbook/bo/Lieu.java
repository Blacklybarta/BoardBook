package fr.eni.ecole.boardbook.bo;

import java.util.ArrayList;
import java.util.List;

public class Lieu {
	private int id;
	private String nom;
	private List<Fiche> fiches = new ArrayList<Fiche>();
	
	public Lieu (){
		
	}
	
	public Lieu(String nom) {
		this.setNom(nom);
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

	public List<Fiche> getFiches() {
		return fiches;
	}

	public void setFiches(List<Fiche> fiches) {
		this.fiches = fiches;
	}
	
	
	@Override
	public String toString() {
		return "Lieu [id=" + id + ", nom=" + nom + "]";
	}
	
	

}
