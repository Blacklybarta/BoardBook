package fr.eni.ecole.boardbook.jdbc;

import java.sql.SQLException;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Vehicule;
import fr.eni.ecole.boardbook.dal.DAO;

public class VehiculeDAOImplJDBC implements DAO<Vehicule>{

	@Override
	public void insert(Vehicule data) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vehicule data) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vehicule selectById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicule selectByMail(String mail, String mdp) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicule> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicule> selectByKeyWord(String recherche) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
