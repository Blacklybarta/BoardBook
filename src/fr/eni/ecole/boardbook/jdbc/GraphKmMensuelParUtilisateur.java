package fr.eni.ecole.boardbook.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Point;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAO;
import fr.eni.ecole.boardbook.dal.DBConnection;

public class GraphKmMensuelParUtilisateur implements DAO<Point<Utilisateur, Integer, Integer>> {

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Point<Utilisateur, Integer, Integer>> listPoint = new ArrayList<>();
	
	private static final String SQL_SELECTALL = "SELECT SUM(nbKmSortie-nbKmEntree) AS TotalKm, "
			+ "MONTH(dateCloture) AS mois,nom,prenom FROM FICHE INNER JOIN RENSEIGNER "
			+ "ON FICHE.idFiche = RENSEIGNER.idFiche INNER JOIN UTILISATEUR "
			+ "ON RENSEIGNER.idUtilisateur = UTILISATEUR.idUtilisateur "
			+ "WHERE MONTH(dateCloture) BETWEEN 1 AND 1 GROUP BY MONTH(dateCloture),nom,prenom";
	
	public void closeConnection(){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con=null;
		}
	}
	
	@Override
	public void insert(Point<Utilisateur, Integer, Integer> data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Point<Utilisateur, Integer, Integer> data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point<Utilisateur, Integer, Integer> selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point<Utilisateur, Integer, Integer> selectByIdentifiant(String identifiant, String mdp)
			throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Point<Utilisateur, Integer, Integer>> selectAll() throws DALException {
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECTALL);

			Point<Utilisateur, Integer, Integer> point = null;
			listPoint.clear();
			
			Utilisateur utilisateur;
			
			while (rs.next()) {
				
				utilisateur = new Utilisateur();

				try {
					utilisateur.setNom(rs.getString("nom"));
					utilisateur.setPrenom(rs.getString("prenom"));
				} catch (ParameterNullException e) {
					e.printStackTrace();
				}
				
				point = new Point<>();
				point.setX(utilisateur);
				point.setY(rs.getInt("TotalKm"));
				point.setZ(rs.getInt("mois"));
				
				listPoint.add(point);
			}
			
			
		} catch (SQLException e) {
			throw new DALException("KmMensuelParUtilisateur failed - ", e);
		} finally {
			try {
				
				if (stmt != null) {
					stmt.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection();
		}
		return listPoint;
	}

	@Override
	public List<Point<Utilisateur, Integer, Integer>> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point<Utilisateur, Integer, Integer> selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
