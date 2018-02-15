package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.bo.exception.ListException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class DoAdminAddUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(String.valueOf(session.getAttribute("administrateur")).equals("true")){
			try {
				Utilisateur utilisateur = null;
				utilisateur = DAOFactory.getUtilisateurDAO().selectById((int) session.getAttribute("idUtilisateur"));
				if (utilisateur != null) {
					req.setAttribute("utilisateur", utilisateur);
					this.getServletContext().getRequestDispatcher("/admin/addUser.jsp").forward(req, resp);
				} else {
					resp.sendRedirect("/BoardBook/connexion.html");
				}
			} catch (DALException e) {
				req.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
			}
		}else{
			resp.sendRedirect("/BoardBook/connexion.html");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nom = String.valueOf(req.getParameter("nom"));
		String prenom = String.valueOf(req.getParameter("prenom"));
		String identifiant = String.valueOf(req.getParameter("identifiant"));
		String mdp = String.valueOf(req.getParameter("mdp"));
		boolean conducteur = Boolean.valueOf(req.getParameter("conducteur"));
		boolean admin = Boolean.valueOf(req.getParameter("administrateur"));

		try {
			Utilisateur utilisateur;
			try {
				utilisateur = new Utilisateur(nom, prenom, identifiant, mdp, conducteur, admin);
				DAOFactory.getUtilisateurDAO().insert(utilisateur);
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
