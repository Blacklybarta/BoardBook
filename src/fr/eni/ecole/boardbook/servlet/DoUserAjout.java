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
import javax.servlet.http.HttpSession;

import fr.eni.ecole.boardbook.bo.Deplacement;
import fr.eni.ecole.boardbook.bo.Fiche;
import fr.eni.ecole.boardbook.bo.Lieu;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.bo.Vehicule;
import fr.eni.ecole.boardbook.bo.exception.ListException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class DoUserAjout extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("idUtilisateur") != null){
			Fiche fiche = null;
			try {
				fiche = DAOFactory.getFicheDAO().selectByUtilisateur((int)session.getAttribute("idUtilisateur"));
			} catch (DALException e1) {
				req.setAttribute("error", e1.getMessage());
				this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
			}
			if(fiche != null){
				req.setAttribute("fiche", fiche);
				req.setAttribute("redirection", "Vous avez déjà une fiche en cours, merci de bien vouloir la clôturer");
				this.getServletContext().getRequestDispatcher("/user/cloture.jsp").forward(req, resp);
			}else{
				try {
					req.setAttribute("listeLieux", DAOFactory.getLieuDAO().selectAll());
					req.setAttribute("listeTypes", DAOFactory.getDeplacementDAO().selectAll());
					req.setAttribute("listeVehicules", DAOFactory.getVehiculeDAO().selectAll());
					req.setAttribute("listeUtilisateurs", DAOFactory.getUtilisateurDAO().selectAll());
					this.getServletContext().getRequestDispatcher("/user/ajout.jsp").forward(req, resp);	
				} catch (DALException e) {
					req.setAttribute("error", e.getMessage());
					this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
				}
			}
			}else{
				req.setAttribute("error", "Merci de vous connecter");
				this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
			}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int idDestination = (Integer.parseInt(req.getParameter("idDestination")));
		int idLieuReception = (Integer.parseInt(req.getParameter("idReception")));
		int idNature = (Integer.parseInt(req.getParameter("idType")));
		int idVehicule = (Integer.parseInt(req.getParameter("idVehicule")));
		int idConducteurPrincipal = (int) req.getSession().getAttribute("idUtilisateur");
		// valider les nom des paramètres
		int nbKmEntree = (Integer.parseInt(req.getParameter("nbKmVehicule")));
		String[] listIdConducteursSecondaires = req.getParameterValues("listeConducteurs");
		String commentaire = req.getParameter("commentaire");

		Lieu lieuArrive = null;
		Lieu lieuDepart = null;
		Deplacement natureDeplacement = null;
		Vehicule vehicule = null;
		List<Utilisateur> listUtilisateur = new ArrayList<>();
		Utilisateur conducteurPrincipale = null;

		GregorianCalendar dateDebut = new GregorianCalendar();

		try {
			lieuArrive = DAOFactory.getLieuDAO().selectById(idDestination);
			lieuDepart = DAOFactory.getLieuDAO().selectById(idLieuReception);
			natureDeplacement = DAOFactory.getDeplacementDAO().selectById(idNature);
			vehicule = DAOFactory.getVehiculeDAO().selectById(idVehicule);
			conducteurPrincipale = DAOFactory.getUtilisateurDAO().selectById(idConducteurPrincipal);

			for (int i = 0; i < listIdConducteursSecondaires.length; ++i) {
				if (!(listIdConducteursSecondaires[i].equals("aucun"))) {
					int id = Integer.parseInt(listIdConducteursSecondaires[i]);
					Utilisateur conducteur = DAOFactory.getUtilisateurDAO().selectById(id);
					listUtilisateur.add(conducteur);
				}
			}
			listUtilisateur.add(conducteurPrincipale);

		} catch (DALException e) {
			req.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}

		Fiche fiche = null;
		try {
			fiche = new Fiche(dateDebut, nbKmEntree, natureDeplacement, vehicule, lieuArrive, lieuDepart,
					listUtilisateur, commentaire);
		} catch (ListException e) {
			req.setAttribute("error", e.getListException());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}

		try {
			DAOFactory.getFicheDAO().insert(fiche);
			resp.sendRedirect("/BoardBook/admin/gestion");
		} catch (DALException e) {
			req.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}

	}

}
