package fr.eni.ecole.boardbook.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {

	public static Connection getConnection()throws DALException {
		Connection cnx = null;
		InitialContext jndi=null;
		DataSource ds=null;
		//----> Obtenir une référence sur le contexte initial JNDI
		try{
		jndi=new InitialContext();}
		catch(NamingException e){
			throw new DALException("Erreur d'accès au contexte initial JNDI");}
		//----> recherche du pool de connexion dans l'annuaire
		try{
		ds=(DataSource)jndi.lookup("java:comp/env/BoardBook");}
		catch(NamingException e){
			throw new DALException("Objet introuvable dans l'arbre JNDI:"+e.getMessage());
		}
		//----> obtenir une connexion
		try{
			cnx = ds.getConnection();
			return cnx;
		}
		catch(SQLException e){
			throw new DALException("Impossible d'obtenir une connexion:"+e.getMessage());}
		}
	
}
