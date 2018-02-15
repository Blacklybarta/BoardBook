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
		String [] idUtiliateurListe = request.getParameterValues("utilisateur");
		int idUtilisateur = Integer.parseInt(idUtiliateurListe[0]);
		int moisDebut = Integer.parseInt(request.getParameter("moisDebut"));
		int moisFin = Integer.parseInt(request.getParameter("moisFin"));
			
		List<Point<Integer, Integer, Boolean>> listPoint = DAOFactory.getGraphKmMensuelParUtilisateur(idUtilisateur, moisDebut, moisFin);
		Manager.createGraphKmUtilisateur(listPoint, idUtilisateur);		
		this.getServletContext().getRequestDispatcher("/statistiques/kmUtilisateurMois.jsp").forward(request, response);			
	}

	
}
