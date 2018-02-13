package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class DoValiderAcces extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Utilisateur utilisateur = null;
		if (session.getAttribute("idUtilisateur") != null) {

			try {
				utilisateur = DAOFactory.getUtilisateurDAO().selectById((int) session.getAttribute("idUtilisateur"));
				if (utilisateur != null) {
					if (utilisateur.isAdministrateur()) {
						req.setAttribute("utilisateur", utilisateur);
						this.getServletContext().getRequestDispatcher("/admin/gestion.jsp").forward(req, resp);
					} else if (utilisateur.isConducteur()) {
						req.setAttribute("utilisateur", utilisateur);
						this.getServletContext().getRequestDispatcher("/user/gestion.jsp").forward(req, resp);
					}
				}
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			resp.sendRedirect("/BoardBook/connexion.html");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String identifiant = req.getParameter("identifiant");
		String mdp = req.getParameter("password");
		HttpSession session = req.getSession();
		Utilisateur utilisateur = null;
		
			try {
				utilisateur = DAOFactory.getUtilisateurDAO().selectByIdentifiant(identifiant, mdp);
				if (utilisateur != null) {
					if (utilisateur.isAdministrateur()) {
						req.setAttribute("utilisateur", utilisateur);
						session.setAttribute("idUtilisateur", utilisateur.getId());
						this.getServletContext().getRequestDispatcher("/admin/gestion.jsp").forward(req, resp);
					} else if (utilisateur.isConducteur()) {
						req.setAttribute("utilisateur", utilisateur);
						session.setAttribute("idUtilisateur", utilisateur.getId());
						this.getServletContext().getRequestDispatcher("/user/gestion.jsp").forward(req, resp);
					} else {
						resp.sendRedirect("errorPage");
					}
				} else {
					resp.sendRedirect("errorPage");
				}
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
