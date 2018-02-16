package fr.eni.ecole.boardbook.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Point;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DBConnection;

public class GraphVehiculeNbJoursUtilisationDAOImplJDBC {

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Point<Integer, Boolean, Boolean>> listPoint = new ArrayList<>();
	
	private static final String SQL_SELECT_BY_ID="SELECT immatriculation, "
			+ "SUM(DATEDIFF(day, dateDepart, dateCloture)) AS NbJoursUtilisation "
			+ "FROM FICHE INNER JOIN VEHICULE ON FICHE.idVehicule = VEHICULE.idVehicule "
			+ "WHERE dateCloture >= ? AND dateCloture <= ? AND VEHICULE.idVehicule = ? GROUP BY immatriculation";
	
	
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
	
	public List<Point<Integer, Boolean, Boolean>> statistiqueVehiculeNbJoursUtilisation(int id, java.util.Date dateDebut, java.util.Date dateFin) throws DALException {
		
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
			//pstmt.setDate(1, dateDebut);
			//pstmt.setDate(2, dateFin);
			pstmt.setInt(3, id);
			ResultSet rs = pstmt.executeQuery();
			
			
			Point<Integer, Boolean, Boolean> point = null;
			listPoint.clear();
			
			if (rs.next()) {
				
				point = new Point<>();
				point.setX(rs.getInt("NbJoursUtilisation"));
				point.setY(false);
				point.setZ(false);
				
				listPoint.add(point);
			}
			
			
		} catch (SQLException e) {
			throw new DALException("VehiculeNbJoursUtilisation failed - ", e);
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
	
}
