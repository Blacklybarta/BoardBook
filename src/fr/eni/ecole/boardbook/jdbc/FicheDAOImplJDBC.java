package fr.eni.ecole.boardbook.jdbc;

import java.sql.SQLException;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Fiche;
import fr.eni.ecole.boardbook.dal.DAO;

public class FicheDAOImplJDBC implements DAO<Fiche>{

	@Override
	public void insert(Fiche data) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Fiche data) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Fiche selectById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fiche selectByMail(String mail, String mdp) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fiche> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fiche> selectByKeyWord(String recherche) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
