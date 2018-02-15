package fr.eni.ecole.boardbook.bll;


import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import fr.eni.ecole.boardbook.bo.Point;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class Manager {
	private final static String [] listMois ={"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};	
	private static JFreeChart graph;

		
	public static JFreeChart getGraphique (){			
		return graph;
	}
	
	public static void createGraphKmDeplacement (){	
		List <Point <String, Integer, Boolean>> listPoint = null;
		try {
			listPoint = DAOFactory.getGraphKmParDeplementDAO().selectAll();
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
	
	
	public static void createGraphKmUtilisateur (List<Point<Utilisateur, Integer, Integer>> listPoint){
	
		String nom = null;
		String prenom = null;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < listPoint.size(); ++i) {
			//if (Manager.idUtilisateur == listPoint.get(i).getX().getId()){
				nom = listPoint.get(i).getX().getNom();
				prenom = listPoint.get(i).getX().getPrenom();
				int mois = listPoint.get(i).getZ() - 1;
				String month = listMois[listPoint.get(i).getZ() - 1];	
				dataset.addValue(listPoint.get(i).getY(),month, "");
				
		}

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
	
	private static JFreeChart createGraphKmConsommationVehicule (){
		
		return null;
	}
	
	private static JFreeChart createGraphNbJourVehicule () {
		
		return null;
	}
	
}
