package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.boardbook.bo.Deplacement;
import fr.eni.ecole.boardbook.bo.exception.ListException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class DoAdminAddType extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(String.valueOf(session.getAttribute("administrateur")).equals("true")){
			this.getServletContext().getRequestDispatcher("/admin/addType.jsp").forward(req, resp);
		}else{
			resp.sendRedirect("/BoardBook/connexion.html");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nom = String.valueOf(req.getParameter("nom"));
		try {
			Deplacement deplacement = new Deplacement(nom);
			deplacement.setActif(true);
			try {
				DAOFactory.getDeplacementDAO().insert(deplacement);
				resp.sendRedirect("/BoardBook/admin/gestion");
			} catch (DALException e) {
				req.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
			}
		} catch (ListException e) {
			req.setAttribute("error", e.getListException());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
		
		
	}
	
}
