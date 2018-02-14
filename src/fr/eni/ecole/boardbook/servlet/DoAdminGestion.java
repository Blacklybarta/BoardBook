package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class DoAdminGestion extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("idUtilisateur") != null) {
			// récupération d'info de connexion dans la session
			if(String.valueOf(session.getAttribute("administrateur")).equals("true")){
				//redirige sur la page admin si == true
				this.getServletContext().getRequestDispatcher("/admin/gestion.jsp").forward(req, resp);
			}else if(String.valueOf(session.getAttribute("conducteur")).equals("true")){
				//redirige sur la page user si == true
				this.getServletContext().getRequestDispatcher("/user/gestion.jsp").forward(req, resp);
			}
		} else {
			// envoie a la connexion si session == null
			resp.sendRedirect("/BoardBook/connexion.html");
		}
		}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//resp.sendRedirect("/admin/gestion.jsp");
	}

}
