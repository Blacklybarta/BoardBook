package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.bo.exception.ListException;
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class DoAdminUpdateUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			List<Utilisateur> listUtilisateur = null;
			
			listUtilisateur = DAOFactory.getUtilisateurDAO().selectAll();
			req.setAttribute("listeUtilisateurs", listUtilisateur);
			this.getServletContext().getRequestDispatcher("/admin/updateUser.jsp").forward(req, resp);
			
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utilisateur utilisateur = null;
		try {
			if(String.valueOf(req.getParameter("select")).equals("true")){
				String[] values = req.getParameterValues("idUser");
				System.out.println(values[0]);
				utilisateur = DAOFactory.getUtilisateurDAO().selectById(Integer.parseInt(values[0]));
				req.setAttribute("utilisateur", utilisateur);
				this.getServletContext().getRequestDispatcher("/admin/updateUser.jsp").forward(req, resp);
			}else if(String.valueOf(req.getParameter("update")).equals("true")){
				String nom = String.valueOf(req.getParameter("nom"));
				String prenom = String.valueOf(req.getParameter("prenom"));
				String identifiant = String.valueOf(req.getParameter("identifiant"));
				String mdp = String.valueOf(req.getParameter("mdp"));
				boolean conducteur = Boolean.valueOf(req.getParameter("conducteur"));
				boolean admin = Boolean.valueOf(req.getParameter("administrateur"));
				try {
					utilisateur = new Utilisateur(nom, prenom, identifiant, mdp, conducteur, admin);
				} catch (ListException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DAOFactory.getUtilisateurDAO().update(utilisateur);
				resp.sendRedirect("/BoardBook/admin/gestion");
			}
		} catch (NumberFormatException | DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
