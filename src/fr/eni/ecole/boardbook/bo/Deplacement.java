package fr.eni.ecole.boardbook.bo;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.boardbook.bo.exception.ListException;
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;

public class Deplacement {
	private int id;
	private String nature;
	private List<Fiche> fiches = new ArrayList<Fiche>();
	private boolean actif;
	
	public Deplacement (){
		
	}
	
	public Deplacement(String nature) throws ListException {
		ListException listE =  new ListException ();
		boolean isException = false;
		try {
			this.setNature(nature);
		} catch (ParameterNullException e) {
			listE.addException(e.getMessage());
			isException = true;
		}
		if (isException){
			throw listE;
		}
	}
		
	
	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
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

	public void setNature(String nature) throws ParameterNullException {
		if (nature != null && nature.trim().length()>0){
			this.nature = nature;
		}else {
			throw new ParameterNullException("La nature du déplacement doit être renseignée");
		}
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
