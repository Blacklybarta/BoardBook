package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.boardbook.bo.Vehicule;
import fr.eni.ecole.boardbook.bo.exception.ListException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class DoAdminAddCar extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(String.valueOf(session.getAttribute("administrateur")).equals("true")){
			this.getServletContext().getRequestDispatcher("/admin/addCar.jsp").forward(req, resp);
		}else{
			resp.sendRedirect("/BoardBook/connexion.html");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String marque = String.valueOf(req.getParameter("marque"));
		String immatriculation = String.valueOf(req.getParameter("immatriculation"));
				
		try {
			Vehicule vehicule = null;
			try {
				// Par defaut le vehicule est disponible lors d'un ajout
				vehicule = new Vehicule(marque, immatriculation, true);
				DAOFactory.getVehiculeDAO().insert(vehicule);
				resp.sendRedirect("/BoardBook/admin/gestion");
			} catch (ListException e) {
				req.setAttribute("error", e.getListException());
				System.out.println(e.getListException());
				this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
			}
		} catch (DALException e) {
			req.setAttribute("error", e.getMessage());
			System.out.println(e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
}
