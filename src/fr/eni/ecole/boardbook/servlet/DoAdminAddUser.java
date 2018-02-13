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
		Utilisateur utilisateur = null;

		try {
			utilisateur = DAOFactory.getUtilisateurDAO().selectById((int) session.getAttribute("idUtilisateur"));
			if (utilisateur != null) {
				req.setAttribute("utilisateur", utilisateur);
				this.getServletContext().getRequestDispatcher("/admin/addUser.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("/BoardBook/connexion.html");
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				if (utilisateur != null) {
					System.out.println(utilisateur.toString());
				}
			} catch (ListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
