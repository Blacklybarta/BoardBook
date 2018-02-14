package fr.eni.ecole.boardbook.bo;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.boardbook.bo.exception.ListException;
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;

public class Vehicule {
	private int id;
	private String marque;
	private String immatriculation;
	private boolean disponible;
	private List<Fiche> fiches = new ArrayList<Fiche>();

	public Vehicule() {

	}

	public Vehicule(String marque, String immatriculation, boolean disponible) throws ListException {		
		ListException listE =  new ListException ();
		boolean isException = false;
		this.disponible = disponible;
		try {
			this.setMarque(marque);
		} catch (ParameterNullException e) {
			listE.addException(e.getMessage());
			isException = true;
		}
		try {
			this.setImmatriculation(immatriculation);
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

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) throws ParameterNullException {		
		if (marque != null && marque.trim().length() > 0){
			this.marque = marque;
		}else {
			throw new ParameterNullException("La marque du véhicule doit être renseignée.");
		}		
	}
		
	

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) throws ParameterNullException {
		if (immatriculation != null && immatriculation.trim().length() > 0){
			this.immatriculation = immatriculation;
		}else {
			throw new ParameterNullException("La plaque d'immatriculation doit être renseignée.");
		}		
	}
		


	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public List<Fiche> getFiches() {
		return fiches;
	}

	private void setFiches(List<Fiche> fiches) {
		this.fiches = fiches;
	}

	@Override
	public String toString() {
		return "Vehicule [id=" + id + ", marque=" + marque + ", immatriculation=" + immatriculation + ", disponible="
				+ disponible + ", fiches=" + fiches + "]";
	}
	

}
