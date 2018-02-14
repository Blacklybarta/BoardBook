package fr.eni.ecole.boardbook.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;

public interface DAO<T> {

	public void insert(T data) throws DALException ;

	public void update(T data) throws DALException ;

	public void delete(int id) throws DALException ;

	public T selectById(int id) throws DALException ;
	
	public T selectByIdentifiant(String identifiant, String mdp) throws DALException;

	public List<T> selectAll() throws DALException ;
	
	public List<T> selectByKeyWord(String recherche) throws DALException;
	
}
