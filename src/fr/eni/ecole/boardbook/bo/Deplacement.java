package fr.eni.ecole.boardbook.bo;

import java.util.ArrayList;
import java.util.List;

public class Deplacement {
	private int id;
	private String nature;
	private List<Fiche> fiches = new ArrayList<Fiche>();
	
	public Deplacement (){
		
	}
	
	public Deplacement(String nature) {
		this.setNature(nature);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}
	
	public List<Fiche> getFiches() {
		return fiches;
	}

	public void setFiches(List<Fiche> fiches) {
		this.fiches = fiches;
	}

	@Override
	public String toString() {
		return "Deplacement [id=" + id + ", nature=" + nature + "]";
	}
	
	
	
	
}
