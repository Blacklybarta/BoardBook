package fr.eni.ecole.boardbook.bo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Fiche {
	private int id;
	private GregorianCalendar dateDepart;
	private GregorianCalendar dateCloture;
	private Double nbKmEntree;
	private Double nbKmSortie;
	private Double carburantNbLitre;
	private Double carburantMontant;
	private boolean cloture;
	private Deplacement natureDeplacement;
	private Vehicule vehiculeLoue;
	private Lieu lieuArrivee;
	private Lieu lieuDepart;
	private List<Utilisateur> conducteur = new ArrayList<Utilisateur>();
	
	
	public Fiche (){
		
	}

	public Fiche(GregorianCalendar dateDepart, Double nbKmEntree) {
		this.setDateDepart(dateDepart);
		this.setNbKmEntree(nbKmEntree);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GregorianCalendar getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(GregorianCalendar dateDepart) {
		this.dateDepart = dateDepart;
	}

	public GregorianCalendar getDateCloture() {
		return dateCloture;
	}

	public void setDateCloture(GregorianCalendar dateCloture) {
		this.dateCloture = dateCloture;
	}

	public Double getNbKmEntree() {
		return nbKmEntree;
	}

	public void setNbKmEntree(Double nbKmEntree) {
		this.nbKmEntree = nbKmEntree;
	}

	public Double getNbKmSortie() {
		return nbKmSortie;
	}

	public void setNbKmSortie(Double nbKmSortie) {
		this.nbKmSortie = nbKmSortie;
	}

	public Double getCarburantNbLitre() {
		return carburantNbLitre;
	}

	public void setCarburantNbLitre(Double carburantNbLitre) {
		this.carburantNbLitre = carburantNbLitre;
	}

	public Double getCarburantMontant() {
		return carburantMontant;
	}

	public void setCarburantMontant(Double carburantMontant) {
		this.carburantMontant = carburantMontant;
	}

	public boolean isCloture() {
		return cloture;
	}

	public void setCloture(boolean cloture) {
		this.cloture = cloture;
	}
	
	public Deplacement getNatureDeplacement() {
		return natureDeplacement;
	}

	public void setNatureDeplacement(Deplacement natureDeplacement) {
		this.natureDeplacement = natureDeplacement;
	}

	public Vehicule getVehiculeLoue() {
		return vehiculeLoue;
	}

	public void setVehiculeLoue(Vehicule vehiculeLoue) {
		this.vehiculeLoue = vehiculeLoue;
	}

	public Lieu getLieuArrivee() {
		return lieuArrivee;
	}

	public void setLieuArrivee(Lieu lieuArrivee) {
		this.lieuArrivee = lieuArrivee;
	}

	public Lieu getLieuDepart() {
		return lieuDepart;
	}

	public void setLieuDepart(Lieu lieuDepart) {
		this.lieuDepart = lieuDepart;
	}

	public List<Utilisateur> getConducteur() {
		return conducteur;
	}

	public void setConducteur(List<Utilisateur> conducteur) {
		this.conducteur = conducteur;
	}

	@Override
	public String toString() {
		return "Fiche [id=" + id + ", dateDepart=" + dateDepart + ", dateCloture=" + dateCloture + ", nbKmEntree="
				+ nbKmEntree + ", nbKmSortie=" + nbKmSortie + ", carburantNbLitre=" + carburantNbLitre
				+ ", carburantMontant=" + carburantMontant + ", cloture=" + cloture + "]";
	}
	
	
	/**
	 * input : nbKmSortie, nbKmEntree, carburantNbLitre
	 * methode permet de calucler la consommation en km/l en divisant le nombre de km parcouru divisé par le volume de carburant 
	 * @return un double : consommation en km/l
	 */
	public double getConsommation (){
		double consommation = (nbKmSortie - nbKmEntree)/carburantNbLitre;		
		return consommation;
	}
	
	
	
	
	
}
