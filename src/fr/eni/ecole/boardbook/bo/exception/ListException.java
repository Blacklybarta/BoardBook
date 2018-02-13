package fr.eni.ecole.boardbook.bo.exception;

import java.util.ArrayList;
import java.util.List;

public class ListException extends Exception{
	private List <String> listException = new ArrayList<>(); 
	
	public ListException (){		
	}
	
	public void  addException (String msg){
		listException.add(msg);
	}
	
	public String getListException (){
		StringBuilder sb = new StringBuilder ();
		sb.append("Liste des erreurs retournées : ");
		for (int i=0; i<listException.size(); ++i){
			sb.append("-");
			sb.append(listException.get(i));
			sb.append(".\n");
		}
		return sb.toString();	
	}
}
