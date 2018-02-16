package fr.eni.ecole.boardbook.bll;


import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import fr.eni.ecole.boardbook.bo.Point;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.bo.Vehicule;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;
import fr.eni.ecole.boardbook.jdbc.GraphKmParDeplementDAOImplJDBC;

public class Manager {
	private final static String [] listMois ={"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};	
	private static JFreeChart graph;

		
	public static JFreeChart getGraphique (){			
		return graph;
	}
	
	public static void createGraphKmDeplacement (){	
		List <Point <String, Integer, Boolean>> listPoint = null;
		GraphKmParDeplementDAOImplJDBC gkpd = DAOFactory.getGraphKmParDeplacement();
		try {
			listPoint = gkpd.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
	      for(int i =0; i< listPoint.size(); ++i){
	    	  dataset.addValue( listPoint.get(i).getY() , listPoint.get(i).getX() , "" );
	      }        
	
		graph = ChartFactory.createBarChart(
	    	         "Nombre de Km par type de déplacement",           
	    	         "Déplacements",            
	    	         "km",            
	    	         dataset,          
	    	         PlotOrientation.VERTICAL,           
	    	         true, true, false);		
	}
	
	
	public static void createGraphKmUtilisateur (List<Point<Integer, Integer, Boolean>> listPoint, int idUtilisateur, int moisDebut, int moisFin){
		Utilisateur utilisateur = null;		
		try {
			utilisateur = DAOFactory.getUtilisateurDAO().selectById(idUtilisateur);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		String nom = utilisateur.getNom();
		String prenom = utilisateur.getPrenom();
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		int y =0;
		
		do {
			dataset.addValue(0,listMois[listPoint.get(y).getX()], "");
			y++;
		}while (moisDebut < listPoint.get(y).getX());
		
			
		for (int i = 0; i < listPoint.size(); ++i) {
				int value = listPoint.get(i).getY();
				dataset.addValue(value,listMois[listPoint.get(i).getX()], "");				
		}
			
		y = listPoint.get(listPoint.size()).getX();
		int z = listPoint.get(listPoint.size()).getX();
		do {
			dataset.addValue(0, listMois[z], "");
			z++;
		}while (y <= moisFin);

		graph = ChartFactory.createBarChart(
				"Nombre de Km par mois de " + nom + " " + prenom,
				"Mois",
				"km", 
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false);
	}
	
	private static void createGraphKmConsommationUtilisateur (){
		
	}
	
	private static void createGraphKmConsommationVehicule (){
		
		
	}
	
	private static void createGraphNbJourVehicule (List<Point<Integer, Integer, Boolean>> listPoint, int idVehicule) {
		Vehicule vehicule = null;		
		try {
			vehicule = DAOFactory.getVehiculeDAO().selectById(idVehicule);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		String nom = vehicule.getMarque();		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
		for (int i = 0; i < listPoint.size(); ++i) {				
				dataset.addValue(listPoint.get(i).getY(),listMois[listPoint.get(i).getX()], "");				
		}

		graph = ChartFactory.createBarChart(
				"Nombre de Km par mois pour le véhicule : " + vehicule.getMarque(), 
				"Mois",
				"km", 
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false);	
	}
	
}
