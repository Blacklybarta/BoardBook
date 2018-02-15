package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.boardbook.bo.Lieu;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.bo.exception.ListException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class DoAdminAddDest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(String.valueOf(session.getAttribute("administrateur")).equals("true")){
			this.getServletContext().getRequestDispatcher("/admin/addDest.jsp").forward(req, resp);
		}else{
			resp.sendRedirect("/BoardBook/connexion.html");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nom = String.valueOf(req.getParameter("nom"));

		try {
			Lieu lieu;
			try {
				lieu = new Lieu(nom);
				DAOFactory.getLieuDAO().insert(lieu);
				resp.sendRedirect("/BoardBook/admin/gestion");
			} catch (ListException e) {
				req.setAttribute("error", e.getListException());
				this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
			}
		} catch (DALException e) {
			req.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}

	}
	
}
