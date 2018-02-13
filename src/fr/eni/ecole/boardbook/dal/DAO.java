package fr.eni.ecole.boardbook.dal;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

	public void insert(T data) throws SQLException ;

	public void update(T data) throws SQLException ;

	public void delete(int id) throws SQLException ;

	public T selectById(int id) throws SQLException ;
	
	public T selectByIdentifiant(String identifiant, String mdp) throws SQLException;

	public List<T> selectAll() throws SQLException ;
	
	public List<T> selectByKeyWord(String recherche) throws SQLException;
	
}
