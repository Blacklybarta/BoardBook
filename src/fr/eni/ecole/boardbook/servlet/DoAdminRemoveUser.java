package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class DoAdminRemoveUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(String.valueOf(session.getAttribute("administrateur")).equals("true")){
			try {
				List<Utilisateur> listUtilisateur;
				//envoi d'une liste d'utilisateur pour la selection
				listUtilisateur = DAOFactory.getUtilisateurDAO().selectAll();
				req.setAttribute("listeUtilisateurs", listUtilisateur);
				this.getServletContext().getRequestDispatcher("/admin/removeUser.jsp").forward(req, resp);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			resp.sendRedirect("/BoardBook/connexion.html");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] values = req.getParameterValues("idUser");
		try {
			//suppression d'une utilisateur et redirection sur la page gestion damin
			DAOFactory.getUtilisateurDAO().delete(Integer.parseInt(values[0]));
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
