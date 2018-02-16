package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.boardbook.bll.Manager;
import fr.eni.ecole.boardbook.bo.Point;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;
import fr.eni.ecole.boardbook.jdbc.GraphKmMensuelParUtilisateur;


public class DoKmUtilisateurMois extends HttpServlet {
	private static final long serialVersionUID = 1L;
       



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Utilisateur> liste = null;
		try {
			liste = DAOFactory.getUtilisateurDAO().selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("listeUtilisateurs", liste );
		getServletContext().getRequestDispatcher("/statistiques/kmUtilisateurMois.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String [] idUtilisateurListe = request.getParameterValues("utilisateur");
		int idUtilisateur = Integer.parseInt(idUtilisateurListe[0]);
		int moisDebut = Integer.parseInt(request.getParameter("moisDebut"));
		int moisFin = Integer.parseInt(request.getParameter("moisFin"));
		int annee = Integer.parseInt(request.getParameter("annee"));
		
		GraphKmMensuelParUtilisateur gmpt = DAOFactory.getGraphKmMensuelParUtilisateur();
		List<Point<Integer, Integer, Boolean>> listPoint = null;
		try {
			listPoint = gmpt.statistiqueKmMensuelUtilisateur(idUtilisateur, moisDebut, moisFin, annee);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Manager.createGraphKmUtilisateur(listPoint, idUtilisateur, moisDebut, moisFin);		
		this.getServletContext().getRequestDispatcher("/statistiques/kmUtilisateurMois.jsp").forward(request, response);			
	}

	
}
