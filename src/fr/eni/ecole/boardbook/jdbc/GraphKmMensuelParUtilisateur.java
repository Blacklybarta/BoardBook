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

public class GraphKmMensuelParUtilisateur {

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Point<Integer, Integer, Boolean>> listPoint = new ArrayList<>();
	
	private static final String SQL_SELECT_BY_ID = "SELECT RENSEIGNER.idUtilisateur,"
			+ "SUM(nbKmSortie-nbKmEntree) AS TotalKm, "
			+ "MONTH(dateCloture) AS mois,nom,prenom FROM FICHE INNER JOIN RENSEIGNER "
			+ "ON FICHE.idFiche = RENSEIGNER.idFiche INNER JOIN UTILISATEUR "
			+ "ON RENSEIGNER.idUtilisateur = UTILISATEUR.idUtilisateur "
			+ "WHERE MONTH(dateCloture) BETWEEN ? AND ? AND RENSEIGNER.idUtilisateur=? "
			+ "AND YEAR(dateCloture)=?"
			+ "GROUP BY MONTH(dateCloture),nom,prenom,RENSEIGNER.idUtilisateur";
	
	
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

	public List<Point<Integer, Integer, Boolean>> statistiqueKmMensuelUtilisateur(int id, int moisDebut, int moisFin,int annee)
			throws DALException {
		// TODO Auto-generated method stub
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, moisDebut);
			pstmt.setInt(2, moisFin);
			pstmt.setInt(3, id);
			pstmt.setInt(4, annee);
			ResultSet rs = pstmt.executeQuery();
			
			Point<Integer, Integer, Boolean> point = null;
			listPoint.clear();
			
			Utilisateur utilisateur;
			
			if (rs.next()) {
				
				utilisateur = new Utilisateur();

				try {
					utilisateur.setNom(rs.getString("nom"));
					utilisateur.setPrenom(rs.getString("prenom"));
				} catch (ParameterNullException e) {
					e.printStackTrace();
				}
				
				point = new Point<>();
				point.setX(rs.getInt("mois"));
				point.setY(rs.getInt("TotalKm"));
				point.setZ(false);
				
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

}
