package fr.eni.ecole.boardbook.bo;

import java.util.ArrayList;
import java.util.List;

public class Vehicule {
	private int id;
	private String marque;
	private String immatriculation;
	private boolean disponible;
	private List<Fiche> fiches = new ArrayList<Fiche>();

	public Vehicule() {

	}

	public Vehicule(String marque, String immatriculation, boolean disponible) {
		this.setMarque(marque);
		this.setImmatriculation(immatriculation);
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

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
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

	public void setFiches(List<Fiche> fiches) {
		this.fiches = fiches;
	}

}
