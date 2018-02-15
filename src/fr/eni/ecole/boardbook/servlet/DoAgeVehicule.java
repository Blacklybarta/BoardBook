package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.boardbook.bll.Manager;
import fr.eni.ecole.boardbook.bo.Point;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.bo.Vehicule;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

/**
 * Servlet implementation class DoAgeVehicule
 */

public class DoAgeVehicule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Vehicule> liste = null;
		try {
			liste = DAOFactory.getVehiculeDAO().selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("listeVehicules", liste);
		getServletContext().getRequestDispatcher("/statistiques/ageVehicule.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int idVehicule = Integer.parseInt(request.getParameter("vehicule"));
		Vehicule vehicule = null;
		try {
			vehicule = DAOFactory.getVehiculeDAO().selectById(idVehicule);
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		GregorianCalendar dateDebut = new GregorianCalendar();
		GregorianCalendar dateFin = new GregorianCalendar();
		DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
		
		try {
			dateDebut.setTime(df.parse(request.getParameter("dateDepart")));
			dateFin.setTime(df.parse(request.getParameter("dateFin")));
		} catch (ParseException e) {
			request.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(request, response);
		}
		
		List<Point<Utilisateur, Integer, Integer>> listPoint = DAOFactory.
	}

}
