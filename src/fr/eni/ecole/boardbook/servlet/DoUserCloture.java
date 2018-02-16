package fr.eni.ecole.boardbook.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.boardbook.bo.Fiche;
import fr.eni.ecole.boardbook.bo.exception.ClotureException;
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class DoUserCloture extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("idUtilisateur") != null) {
			try {
				Fiche fiche = DAOFactory.getFicheDAO().selectByUtilisateur((int) session.getAttribute("idUtilisateur"));
				if (fiche != null) {
					req.setAttribute("fiche", fiche);
					this.getServletContext().getRequestDispatcher("/user/cloture.jsp").forward(req, resp);
				} else {
					req.setAttribute("error", "Aucune fiche vous correspondant n'est ouverte");
					this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
				}
			} catch (DALException e) {
				req.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("error", "Merci de vous connecter");
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int nbKmSortie = Integer.parseInt(req.getParameter("nbKmSortie"));
		double carburantNbLitre = Double.parseDouble(req.getParameter("carburantNbLitre"));
		double carburantMontant = Double.parseDouble(req.getParameter("carburantMontant"));
		String debut = String.valueOf(req.getParameter("dateDepart"));
		System.out.println(debut);
		try {
			Fiche fiche = new Fiche();
			fiche.setNbKmSortie(nbKmSortie);
			fiche.setCarburantNbLitre(carburantNbLitre);
			fiche.setCarburantMontant(carburantMontant);
			GregorianCalendar greg = new GregorianCalendar();
			fiche.setCloture(true);
			fiche.setId(Integer.parseInt(req.getParameter("idFiche")));
			
			String[] dateDebut = debut.split("/");
			int dd = Integer.parseInt(dateDebut[0]);
			int mm = Integer.parseInt(dateDebut[1]);	
			int yy = Integer.parseInt(dateDebut[2]);
			GregorianCalendar gregDebut = new GregorianCalendar(yy, mm-1, dd);
			try {
				fiche.setDateCloture(greg);
			} catch (ClotureException e) {
				req.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
			}
			try {
				if(greg.before(gregDebut)){
					req.setAttribute("error", "La date de fin ne peut être antérieure à la date de début");
					this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
				}else{
					DAOFactory.getFicheDAO().update(fiche);
				}
				
			} catch (DALException e) {
				req.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
			}
			resp.sendRedirect("/BoardBook/admin/gestion");
		} catch (ParameterNullException e) {
			req.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		} catch (NumberFormatException e) {
			req.setAttribute("error", e.getMessage());
			this.getServletContext().getRequestDispatcher("/erreur.jsp").forward(req, resp);
		}
	}
}
