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
import javax.servlet.http.HttpSession;

import fr.eni.ecole.boardbook.bll.Manager;
import fr.eni.ecole.boardbook.bo.Point;
import fr.eni.ecole.boardbook.bo.Vehicule;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;
import fr.eni.ecole.boardbook.jdbc.GraphVehiculeNbJoursUtilisationDAOImplJDBC;

/**
 * Servlet implementation class DoAgeVehicule
 */

public class DoAgeVehicule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(String.valueOf(session.getAttribute("administrateur")).equals("true")){
			List<Vehicule> liste = null;
			try {
				liste = DAOFactory.getVehiculeDAO().selectAll();
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("listeVehicules", liste);
			getServletContext().getRequestDispatcher("/statistiques/ageVehicule.jsp").forward(request, response);
		}else{
			request.setAttribute("error", "Droit insuffisant, prendre contact avec un ADMINISTRATEUR");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(request, response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		
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
		
		GraphVehiculeNbJoursUtilisationDAOImplJDBC gkv = DAOFactory.getGraphVehiculeNbJoursUtilisation();
		try {
			List<Point<String, Integer, Boolean>> listPoint = gkv.statistiqueVehiculeNbJoursUtilisation(dateDebut, dateFin);
			Manager.createGraphNbJourVehicule(listPoint);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/statistiques/ageVehicule.jsp").forward(request, response);
	}

}
