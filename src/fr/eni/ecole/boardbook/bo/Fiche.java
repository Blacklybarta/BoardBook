package fr.eni.ecole.boardbook.bo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import fr.eni.ecole.boardbook.bo.exception.ClotureException;
import fr.eni.ecole.boardbook.bo.exception.ListException;
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;

public class Fiche {
	private int id;
	private GregorianCalendar dateDepart;
	private GregorianCalendar dateCloture;
	private int nbKmEntree;
	private int nbKmSortie;
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

	public Fiche(GregorianCalendar dateDepart, int nbKmEntree) throws ListException {
		ListException listE =  new ListException ();
		boolean isException = false;
		
		try {
			this.setDateDepart(dateDepart);
		} catch (ParameterNullException e) {
			listE.addException(e.getMessage());
			isException = true;
		}
		try {
			this.setNbKmEntree(nbKmEntree);
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

	public GregorianCalendar getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(GregorianCalendar dateDepart) throws ParameterNullException {
		if (dateDepart != null){
			this.dateDepart = dateDepart;
		}else {
			throw new ParameterNullException("La date de départ doit être renseignée.");
		}
	}

	public GregorianCalendar getDateCloture() {
		return dateCloture;
	}

	public void setDateCloture(GregorianCalendar dateCloture) throws ClotureException {
		if (dateCloture.before(dateDepart)){
			throw new ClotureException("La date de cloture doit être posterieur à la date de départ.");
		}	
		this.dateCloture = dateCloture;
	}

	public int getNbKmEntree() {
		return nbKmEntree;
	}

	public void setNbKmEntree(int nbKmEntree) throws ParameterNullException {
		if (nbKmEntree > 0){
			this.nbKmEntree = nbKmEntree;
		}else {
			throw new ParameterNullException("Le nombre de km au départ du véhicule doit être renseigné.");
		}
	}

	public int getNbKmSortie() {
		return nbKmSortie;
	}

	public void setNbKmSortie(int nbKmSortie) throws ParameterNullException {
		if (nbKmSortie > nbKmEntree ){
			this.nbKmSortie = nbKmSortie;
		}else {
			throw new ParameterNullException("Le nombre de km au retour du véhicule doit être inferieur au nombre de km renseigné au départ du véhicule.");
		}				
	}

	public Double getCarburantNbLitre() {
		return carburantNbLitre;
	}

	public void setCarburantNbLitre(Double carburantNbLitre) throws ParameterNullException {
		if (carburantNbLitre > 0 ){
			this.carburantNbLitre = carburantNbLitre;
		}else {
			throw new ParameterNullException("Le volume de carburant ajouté doit être superieur à 0.");
		}
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

	public void setNatureDeplacement(Deplacement natureDeplacement) throws ParameterNullException {
		if (natureDeplacement != null ){
			this.natureDeplacement = natureDeplacement;
		}else {
			throw new ParameterNullException("La nature du déplacement doit être renseignée.");
		}
	}
		
	public Vehicule getVehiculeLoue() {
		return vehiculeLoue;
	}

	public void setVehiculeLoue(Vehicule vehiculeLoue) throws ParameterNullException {
		if (vehiculeLoue != null ){
			this.vehiculeLoue = vehiculeLoue;
		}else {
			throw new ParameterNullException("Un véhicule doit être renseigné pour effectuer un déplacement.");
		}
	}
			

	public Lieu getLieuArrivee() {
		return lieuArrivee;
	}

	public void setLieuArrivee(Lieu lieuArrivee) throws ParameterNullException {
		if (lieuArrivee != null ){
			this.lieuArrivee = lieuArrivee;
		}else {
			throw new ParameterNullException("Un lieu d'arrivé doit être défini");
		}
	}
		

	public Lieu getLieuDepart() {
		return lieuDepart;
	}

	public void setLieuDepart(Lieu lieuDepart) throws ParameterNullException {
		if (lieuArrivee != null ){
			this.lieuDepart = lieuDepart;
		}else {
			throw new ParameterNullException("Un lieu de départ doit être défini");
		}
	}
		

	public List<Utilisateur> getConducteur() {
		return conducteur;
	}

	private void setConducteur(List<Utilisateur> conducteur) {
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
