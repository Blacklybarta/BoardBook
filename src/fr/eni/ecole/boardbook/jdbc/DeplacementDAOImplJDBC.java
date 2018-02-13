package fr.eni.ecole.boardbook.jdbc;

import java.sql.SQLException;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Deplacement;
import fr.eni.ecole.boardbook.dal.DAO;

public class DeplacementDAOImplJDBC implements DAO<Deplacement>{

	@Override
	public void insert(Deplacement data) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Deplacement data) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Deplacement selectById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Deplacement selectByMail(String mail, String mdp) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deplacement> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deplacement> selectByKeyWord(String recherche) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
