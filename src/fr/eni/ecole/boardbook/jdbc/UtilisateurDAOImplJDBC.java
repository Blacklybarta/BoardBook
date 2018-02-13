package fr.eni.ecole.boardbook.jdbc;

import java.sql.SQLException;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.dal.DAO;

public class UtilisateurDAOImplJDBC implements DAO<Utilisateur>{

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
	public Utilisateur selectByMail(String mail, String mdp) throws SQLException {
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
