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
	public final static int KM_PAR_MOIS_PAR_UTILISATEUR = 1;
	public final static int KM_PAR_TYPE_DEPLACEMENT = 2;
	public final static int CONSOMMATION_AU_100KM_PAR_UTILISATEUR = 3;
	public final static int CONSOMMATION_PAR_VEHICULE_AU_100KM = 4;
	public final static int NB_JOURS_UTILISATION_VEHICULE = 5;
	private final static String [] listMois ={"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
	
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
		List<Point<Utilisateur, Integer, Integer>> listPoint = null;

		listPoint = (List<Point<Utilisateur, Integer, Integer>>) DAOFactory.getGraphKmMensuelParUtilisateur();

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < listPoint.size(); ++i) {
			int mois = listPoint.get(i).getZ() - 1;
			String month = listMois[listPoint.get(i).getZ() - 1];

			dataset.addValue(listPoint.get(i).getY(),
					listPoint.get(i).getX().getNom() + " " + listPoint.get(i).getX().getPrenom(), month);
		}

		JFreeChart graph = ChartFactory.createBarChart("Nombre de Km par mois et par utilisateur", "Déplacements",
				"Nb km", dataset, PlotOrientation.VERTICAL, true, true, false);
		return graph;
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
