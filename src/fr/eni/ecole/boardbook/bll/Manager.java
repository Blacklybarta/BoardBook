package fr.eni.ecole.boardbook.bll;


import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import fr.eni.ecole.boardbook.bo.Point;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class Manager {
	public final static int KM_PAR_MOIS_PAR_UTILISATEUR = 1;
	public final static int KM_PAR_TYPE_DEPLACEMENT = 2;
	public final static int CONSOMMATION_AU_100KM_PAR_UTILISATEUR = 3;
	public final static int CONSOMMATION_PAR_VEHICULE_AU_100KM = 4;
	public final static int NB_JOURS_UTILISATION_VEHICULE = 5;
	
	public static int choix;

	
	public static JFreeChart getGraphique (){
		JFreeChart graph = null;
		if (choix >0 && choix <6){
			switch (choix) {
			case KM_PAR_MOIS_PAR_UTILISATEUR:
				graph = createGraphKmUtilisateur ();
				break;
			case KM_PAR_TYPE_DEPLACEMENT:
				graph = createGraphKmDeplacement ();
				break;
			case CONSOMMATION_AU_100KM_PAR_UTILISATEUR:
				graph = createGraphKmConsommationUtilisateur ();
				break;	
			case CONSOMMATION_PAR_VEHICULE_AU_100KM:
				graph = createGraphKmConsommationVehicule ();
				break;
			case NB_JOURS_UTILISATION_VEHICULE:
				graph = createGraphNbJourVehicule ();
				break;	
			}
		}
		
		return graph;
	}
	
	private static JFreeChart createGraphKmDeplacement (){	
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
	          
	      //	      dataset.addValue( 3.0 , fiat , userrating );        
	
		JFreeChart graph = ChartFactory.createBarChart(
	    	         "Nombre de Km par mois et par utilisateur",           
	    	         "Déplacements",            
	    	         "Nb km",            
	    	         dataset,          
	    	         PlotOrientation.VERTICAL,           
	    	         true, true, false);	
		return graph;
	}
	
	
	private static JFreeChart createGraphKmUtilisateur (){

		
		return null;
	}
	
	private static JFreeChart createGraphKmConsommationUtilisateur (){
		return null;
	}
	
	private static JFreeChart createGraphKmConsommationVehicule (){
		return null;
	}
	
	private static JFreeChart createGraphNbJourVehicule () {
		return null;
	}
	
}
