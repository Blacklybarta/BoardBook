package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.boardbook.bo.Fiche;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class DoUserCloture extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("idUtilisateur") != null){
			try {
				Fiche fiche = DAOFactory.getFicheDAO().selectByUtilisateur((int)session.getAttribute("idUtilisateur"));
				if(fiche != null){
					req.setAttribute("fiche", fiche);
					this.getServletContext().getRequestDispatcher("/user/cloture.jsp").forward(req, resp);
				}else{
					req.setAttribute("error", "Aucune fiche vous correspondant n'est ouverte");
					this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
				}	
			} catch (DALException e) {
				req.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
			}
		}else{
			req.setAttribute("error", "Merci de vous connecter");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
