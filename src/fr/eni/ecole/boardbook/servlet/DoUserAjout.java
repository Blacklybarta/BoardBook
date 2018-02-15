package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.boardbook.bo.Deplacement;
import fr.eni.ecole.boardbook.bo.Fiche;
import fr.eni.ecole.boardbook.bo.Lieu;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.bo.Vehicule;
import fr.eni.ecole.boardbook.bo.exception.ListException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class DoUserAjout extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		try {
			req.setAttribute("listeLieux", DAOFactory.getLieuDAO().selectAll());
			req.setAttribute("listeTypes", DAOFactory.getDeplacementDAO().selectAll());
			req.setAttribute("listeVehicules", DAOFactory.getVehiculeDAO().selectAll());
			req.setAttribute("listeUtilisateurs", DAOFactory.getUtilisateurDAO().selectAll());
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		getServletContext().getRequestDispatcher("/user/ajout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int idDestination =  (Integer.parseInt(req.getParameter("idDestination")));
		int idLieuReception = (Integer.parseInt(req.getParameter("idReception")));
		int idNature = (Integer.parseInt(req.getParameter("idType")));
		int idVehicule = (Integer.parseInt(req.getParameter("idVehicule")));
		int idConducteurPrincipal = (int) req.getSession().getAttribute("idUtilisateur") ;
		//valider les nom des paramètres
		int nbKmEntree = (Integer.parseInt(req.getParameter("nbKmVehicule")));
		String [] listIdConducteursSecondaires = req.getParameterValues("listeConducteurs");
		String commentaire = req.getParameter("commentaire");

		
		Lieu lieuArrive = null;
		Lieu lieuDepart = null;
		Deplacement natureDeplacement = null;
		Vehicule vehicule = null;
		List <Utilisateur> listUtilisateur = new ArrayList<>();
		Utilisateur conducteurPrincipale = null;
		
		GregorianCalendar dateDebut = new GregorianCalendar();
		DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
		
		try {
			dateDebut.setTime(df.parse(req.getParameter("dateDepart")));		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			lieuArrive =  DAOFactory.getLieuDAO().selectById(idDestination);
			lieuDepart =  DAOFactory.getLieuDAO().selectById(idLieuReception);
			natureDeplacement = DAOFactory.getDeplacementDAO().selectById(idNature);
			vehicule = DAOFactory.getVehiculeDAO().selectById(idVehicule);
			conducteurPrincipale = DAOFactory.getUtilisateurDAO().selectById(idConducteurPrincipal);
			
			for (int i=0; i <listIdConducteursSecondaires.length; ++i){
				int id = Integer.parseInt(listIdConducteursSecondaires [i]);
				Utilisateur conducteur = DAOFactory.getUtilisateurDAO().selectById(id);
				listUtilisateur.add(conducteur);
			}			
			
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Fiche fiche = null;
		try {
			fiche = new Fiche (dateDebut,
					nbKmEntree, 
					natureDeplacement,
					vehicule,
					lieuArrive,
					lieuDepart,
					listUtilisateur,
					commentaire);
		} catch (ListException e) {
			req.setAttribute("error", e.getListException());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
		
		try {
			DAOFactory.getFormationDAO().insert(fiche);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
