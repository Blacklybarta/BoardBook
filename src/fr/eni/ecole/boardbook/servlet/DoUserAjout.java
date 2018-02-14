package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;

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

		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		getServletContext().getRequestDispatcher("/user/ajout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String destination = (String) req.getAttribute("destination");
		String lieuReception = (String) req.getAttribute("lieuReception");
		String nature = (String) req.getAttribute("nature");
		String vehicule = (String) req.getAttribute("vehicule");
		
	}
	
}
