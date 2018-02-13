package fr.eni.ecole.boardbook.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Fiche;
import fr.eni.ecole.boardbook.bo.Lieu;
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
	 * Par defaut, un lieu est toujours actif à l'insertion
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
	 * Un lieu ne peut pas être supprimé, il devient inactif
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
		return null;
	}


	@Override
	public List<Lieu> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
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

}
