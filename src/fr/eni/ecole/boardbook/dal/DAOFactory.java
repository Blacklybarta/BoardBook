package fr.eni.ecole.boardbook.dal;

import fr.eni.ecole.boardbook.bo.Deplacement;
import fr.eni.ecole.boardbook.bo.Fiche;
import fr.eni.ecole.boardbook.bo.Lieu;
import fr.eni.ecole.boardbook.bo.Point;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.bo.Vehicule;
import fr.eni.ecole.boardbook.jdbc.DeplacementDAOImplJDBC;
import fr.eni.ecole.boardbook.jdbc.FicheDAOImplJDBC;
import fr.eni.ecole.boardbook.jdbc.GraphKmMensuelParUtilisateur;
import fr.eni.ecole.boardbook.jdbc.GraphKmParDeplementDAOImplJDBC;
import fr.eni.ecole.boardbook.jdbc.GraphVehiculeNbJoursUtilisationDAOImplJDBC;
import fr.eni.ecole.boardbook.jdbc.LieuDAOImplJDBC;
import fr.eni.ecole.boardbook.jdbc.UtilisateurDAOImplJDBC;
import fr.eni.ecole.boardbook.jdbc.VehiculeDAOImplJDBC;

public class DAOFactory {

	public static DAO<Fiche> getFicheDAO(){
		DAO<Fiche> ficheDAO = null;
		ficheDAO = new FicheDAOImplJDBC();
		return ficheDAO;
	}
	
	public static DAO<Vehicule> getVehiculeDAO(){
		DAO<Vehicule> vehiculeDAO = null;
		vehiculeDAO = new VehiculeDAOImplJDBC();
		return vehiculeDAO;
	}
	
	public static DAO<Deplacement> getDeplacementDAO(){
		DAO<Deplacement> deplacementDAO = null;
		deplacementDAO = new DeplacementDAOImplJDBC();
		return deplacementDAO;
	}
	
	public static DAO<Lieu> getLieuDAO(){
		DAO<Lieu> lieuDAO = null;
		lieuDAO = new LieuDAOImplJDBC();
		return lieuDAO;
	}
	
	public static DAO<Utilisateur> getUtilisateurDAO(){
		DAO<Utilisateur> utilisateurDAO = null;
		utilisateurDAO = new UtilisateurDAOImplJDBC();
		return utilisateurDAO;
	}
	
	/**
	 *  --  Partie graphique  --
	 */
	
	public static GraphKmParDeplementDAOImplJDBC getGraphKmParDeplacement(){
		GraphKmParDeplementDAOImplJDBC graphKmParDeplacement = new GraphKmParDeplementDAOImplJDBC();
		return graphKmParDeplacement;
	}
	
	public static GraphKmMensuelParUtilisateur getGraphKmMensuelParUtilisateur() {
		GraphKmMensuelParUtilisateur graphKmMensuelParUtilisateur = new GraphKmMensuelParUtilisateur();
		return graphKmMensuelParUtilisateur;
	}
	
	public static GraphVehiculeNbJoursUtilisationDAOImplJDBC getGraphVehiculeNbJoursUtilisation() {
		GraphVehiculeNbJoursUtilisationDAOImplJDBC graphVehiculeNbJoursUtilisation = new GraphVehiculeNbJoursUtilisationDAOImplJDBC();
		return graphVehiculeNbJoursUtilisation;
	}
	
}
