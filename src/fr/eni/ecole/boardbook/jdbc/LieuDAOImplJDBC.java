package fr.eni.ecole.boardbook.jdbc;

import java.sql.SQLException;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Lieu;
import fr.eni.ecole.boardbook.dal.DAO;

public class LieuDAOImplJDBC implements DAO<Lieu>{

	@Override
	public void insert(Lieu data) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Lieu data) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Lieu selectById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lieu selectByMail(String mail, String mdp) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lieu> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lieu> selectByKeyWord(String recherche) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}