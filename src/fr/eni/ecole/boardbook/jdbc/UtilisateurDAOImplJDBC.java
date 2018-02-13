package fr.eni.ecole.boardbook.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Fiche;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.dal.DAO;

public class UtilisateurDAOImplJDBC implements DAO<Utilisateur>{
	
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Utilisateur> listUtilisateurs = new ArrayList<>();

	@Override
	public void insert(Utilisateur data) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Utilisateur data) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utilisateur selectById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur selectByIdentifiant(String identifiant, String mdp) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> selectByKeyWord(String recherche) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
