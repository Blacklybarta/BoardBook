package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.boardbook.bo.Vehicule;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class DoAdminRemoveCar extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			List<Vehicule> listVehicule = DAOFactory.getVehiculeDAO().selectAll();
			req.setAttribute("listeVehicules", listVehicule);
			this.getServletContext().getRequestDispatcher("/admin/removeCar.jsp").forward(req, resp);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] values = req.getParameterValues("idVehicule");
		try {
			//suppression d'une utilisateur et redirection sur la page gestion damin
			DAOFactory.getVehiculeDAO().delete(Integer.parseInt(values[0]));
			resp.sendRedirect("/BoardBook/admin/gestion");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
