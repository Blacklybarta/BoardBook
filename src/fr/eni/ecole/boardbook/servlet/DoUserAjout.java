package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String destination = (String) req.getParameter("destination");
		String lieuReception = (String) req.getParameter("lieuReception");
		String nature = (String) req.getParameter("nature");
		String vehicule = (String) req.getParameter("vehicule");
		int idConducteurPrincipal = (int) req.getSession().getAttribute("idUtilisateur") ;
		//valider les nom des paramètres
		int nbKmEntree = (Integer.parseInt(req.getParameter("NbKmEntree")));
		int idLieuDepard= (Integer.parseInt(req.getParameter("LieuDepart")));
		int idLieuArrive= (Integer.parseInt(req.getParameter("LieuArrive")));
		String [] listIdConducteursSecondaires = req.getParameterValues("listeUtilisateurs");
		String commentaire = req.getParameter("commentaire");
		
		
		
	}
	
}
