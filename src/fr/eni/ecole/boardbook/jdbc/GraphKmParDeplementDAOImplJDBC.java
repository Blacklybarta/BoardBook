package fr.eni.ecole.boardbook.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Point;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAO;
import fr.eni.ecole.boardbook.dal.DBConnection;

public class GraphKmParDeplementDAOImplJDBC implements DAO<Point<String, Integer, Boolean>> {

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Point<String, Integer, Boolean>> listPoint = new ArrayList<>();
	
	private static final String SQL_SELECTALL = "Select SUM(nbKmSortie - nbKmEntree) AS nbKilometre"
			+ ", nature FROM FICHE INNER JOIN DEPLACEMENT ON "
			+ "FICHE.idDeplacement = DEPLACEMENT.idDeplacement GROUP BY nature;";
	
	
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
	public void insert(Point data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Point data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Point<String, Integer, Boolean>> selectAll() throws DALException {
		
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECTALL);

			Point<String, Integer, Boolean> point = null;
			listPoint.clear();
			
			while (rs.next()) {
				
				point = new Point<>();
				point.setX(rs.getString("nature"));
				point.setY(rs.getInt("nbKilometre"));
				point.setZ(false);
				
				listPoint.add(point);
			}
			
			
		} catch (SQLException e) {
			throw new DALException("KmParNatureDeplacement failed - ", e);
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
	public List<Point<String, Integer, Boolean>> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
