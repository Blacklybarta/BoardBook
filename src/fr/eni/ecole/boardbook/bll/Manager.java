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
	private final static String [] listMois ={"Jan", "Fev", "Mar", "Avr", "Mai", "Jui", "Jui", "Aoû", "Sep", "Oct", "Nov", "Déc"};	
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
		while (moisDebut < listPoint.get(y).getX()){
			dataset.addValue(0,"", listMois[listPoint.get(y).getX()-1]);
			y++;
		};
		
			
		for (int i = 0; i < listPoint.size(); ++i) {
				int value = listPoint.get(i).getY();
				dataset.addValue(value,"", listMois[listPoint.get(i).getX()-1]);
		}
			
		
		int moisX = listPoint.get(listPoint.size()-1).getX();	
		while (moisX < moisFin){
			dataset.addValue(0,"", listMois[moisX]);
			moisX++;
		};
		

		graph = ChartFactory.createBarChart(
				"Nombre de Km par mois de " + nom + " " + prenom,
				"Mois",
				"km", 
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false);
		graph.removeLegend();
		
	}
	
	private static void createGraphKmConsommationUtilisateur (){
		
	}
	
	private static void createGraphKmConsommationVehicule (){
		
		
	}
	
	public static void createGraphNbJourVehicule (List<Point<String, Integer, Boolean>> listPoint) {		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
		for (int i = 0; i < listPoint.size(); ++i) {				
				dataset.addValue(listPoint.get(i).getY(),listPoint.get(i).getX(), "");				
		}
		
		graph = ChartFactory.createBarChart(
				"Age des véhicules ", 
				"Nb jours",
				"Véhicule", 
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false);	

	}
	
}
