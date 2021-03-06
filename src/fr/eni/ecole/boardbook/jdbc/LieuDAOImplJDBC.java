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
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;
import fr.eni.ecole.boardbook.dal.DAO;
import fr.eni.ecole.boardbook.dal.DBConnection;
import fr.eni.ecole.boardbook.dal.DALException;

public class LieuDAOImplJDBC implements DAO<Lieu>{

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Lieu> listLieux = new ArrayList<>();
	
	private static final String SQL_INSERT = "INSERT INTO LIEU(nom,actif) VALUES(?,?)";
	private static final String SQL_DELETE = "UPDATE LIEU SET actif=? WHERE idLieu=?";
	private static final String SQL_SELECTALL = "SELECT * FROM LIEU";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM LIEU WHERE idLieu=?";
	
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
	
	/**
	 * Par defaut, un lieu est toujours actif � l'insertion
	 */
	@Override
	public void insert(Lieu data) throws DALException {
		// TODO Auto-generated method stub
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, data.getNom());
			pstmt.setBoolean(2, true);

			int nbRows = pstmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					data.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Insert lieu failed - " + data, e);
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
	public void update(Lieu data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Un lieu ne peut pas �tre supprim�, il devient inactif
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
			throw new DALException("delete lieu failed - " + e);
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
	public Lieu selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Lieu lieu = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				lieu = new Lieu();
				
				lieu.setId(rs.getInt("idLieu"));
				lieu.setNom(rs.getString("nom"));
				lieu.setActif(rs.getBoolean("actif"));
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
		return lieu;
	}


	@Override
	public List<Lieu> selectAll() throws DALException {
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECTALL);

			Lieu lieu = null;
			listLieux.clear();
			while (rs.next()) {
				
				lieu = new Lieu();
				
				try {
					lieu.setNom(rs.getString("nom"));
				} catch (ParameterNullException e) {
					e.printStackTrace();
				}
				
				lieu.setId(rs.getInt("idLieu"));
				lieu.setActif(rs.getBoolean("actif"));

				
				listLieux.add(lieu);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll lieu failed - ", e);
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
		return listLieux;
	}

	@Override
	public List<Lieu> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lieu selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lieu selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lieu> selectAllById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
