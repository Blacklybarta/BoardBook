package fr.eni.ecole.boardbook.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Lieu;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.bo.Vehicule;
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAO;
import fr.eni.ecole.boardbook.dal.DBConnection;

public class VehiculeDAOImplJDBC implements DAO<Vehicule>{

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Vehicule> listVehicules = new ArrayList<>();
	
	private static final String SQL_INSERT = "INSERT INTO VEHICULE(immatriculation,marque,disponible) VALUES(?,?,?)";
	private static final String SQL_DELETE = "UPDATE VEHICULE SET disponible=? WHERE idVehicule=?";
	private static final String SQL_SELECTALL = "SELECT * FROM VEHICULE";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM VEHICULE WHERE idVehicule=?";
	
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
	public void insert(Vehicule data) throws DALException {
		// TODO Auto-generated method stub
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, data.getImmatriculation());
			pstmt.setString(2, data.getMarque());
			pstmt.setBoolean(3, data.isDisponible());
		
			int nbRows = pstmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					data.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Insert vehicule failed - " + data, e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}
			closeConnection();
		}
	}

	@Override
	public void update(Vehicule data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * On ne supprime pas un vehicule, on le rend indisponible
	 */
	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_DELETE);
			
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("delete vehicule failed - " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();

		}
	}

	@Override
	public Vehicule selectById(int id) throws DALException {
		ResultSet rs = null;
		Vehicule vehicule = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				vehicule = new Vehicule();
				
				vehicule.setId(rs.getInt("idVehicule"));
				vehicule.setImmatriculation(rs.getString("immatriculation"));
				vehicule.setMarque(rs.getString("marque"));
				vehicule.setDisponible(rs.getBoolean("disponible"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return vehicule;
	}


	@Override
	public List<Vehicule> selectAll() throws DALException {
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECTALL);

			Vehicule vehicule = null;
			listVehicules.clear();
			while (rs.next()) {
				
				vehicule = new Vehicule();
				
				vehicule.setDisponible(rs.getBoolean("disponible"));
				vehicule.setId(rs.getInt("idVehicule"));
				
				try {
					vehicule.setMarque(rs.getString("marque"));
					vehicule.setImmatriculation(rs.getString("immatriculation"));
				} catch (ParameterNullException e) {
					e.printStackTrace();
				}
				
				listVehicules.add(vehicule);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll vehicule failed - ", e);
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
		return listVehicules;
	}

	@Override
	public List<Vehicule> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicule selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicule selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicule> selectAllById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
