package fr.eni.ecole.boardbook.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DBConnection;

public class StatistiqueDAOImplJDBC {
	
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	
	private static final String SQL_KM_PAR_NATURE_DEPLACEMENT = "Select SUM(nbKmSortie - nbKmEntree) AS nbKilometre"
			+ ", nature FROM FICHE INNER JOIN DEPLACEMENT ON "
			+ "FICHE.idDeplacement = DEPLACEMENT.idDeplacement GROUP BY nature;";
	private static final String SQL_CONSOMMATION_PAR_UTILISATEUR = "";
	private static final String SQL_KM_PAR_MOIS_PAR_UTILISATEUR = "";
	private static final String SQL_VEHICULE_NBJOURS_UTILISATION = "";
	private static final String SQL_MOYENNE_CARBURANT_KM_POUR_100 = "";
	
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
	
	public HashMap<String, Integer> KmParNatureDeplacement() throws DALException {
		
		con = null;
		stmt = null;
		ResultSet rs = null;
		HashMap<String, Integer> tabKmNature = new HashMap<String,Integer>();
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_KM_PAR_NATURE_DEPLACEMENT);
			Utilisateur utilisateur = null;

			while (rs.next()) {
				tabKmNature.put(rs.getString("nature"), rs.getInt("nbKilometre"));
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
		return tabKmNature;

	}
	
}
