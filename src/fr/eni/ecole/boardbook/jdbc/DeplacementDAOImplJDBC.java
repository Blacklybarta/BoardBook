package fr.eni.ecole.boardbook.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Deplacement;
import fr.eni.ecole.boardbook.bo.Lieu;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAO;
import fr.eni.ecole.boardbook.dal.DBConnection;

public class DeplacementDAOImplJDBC implements DAO<Deplacement>{
	
	private static final String SQL_INSERT = "INSERT INTO DEPLACEMENT (nature,actif) VALUES (?,?)";
	private static final String SQL_DELETE = "UPDATE DEPLACEMENT SET actif=? WHERE idDeplacement=?";
	private static final String SQL_SELECTALL = "SELECT * FROM DEPLACEMENT";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM DEPLACEMENT WHERE idDeplacement=?";
	
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Deplacement> listDeplacements = new ArrayList<>();
	
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
	public void insert(Deplacement data) throws DALException {
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, data.getNature());
			pstmt.setBoolean(2, data.isActif());
			int nbRows = pstmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet key = pstmt.getGeneratedKeys();
				if (key.next()) {
					data.setId(key.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Insert lieu failed - " + data, e);
		}finally {
			closeConnection();
		}
	}

	@Override
	public void update(Deplacement data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_DELETE);
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("delete failed - ", e);
		}finally {
			closeConnection();
		}
		
	}

	@Override
	public Deplacement selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Deplacement deplacement = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				deplacement = new Deplacement();
				deplacement.setId(rs.getInt("idDeplacement"));
				deplacement.setNature(rs.getString("nature"));
				deplacement.setActif(rs.getBoolean("actif"));
	
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
		return deplacement;
	}


	@Override
	public List<Deplacement> selectAll() throws DALException {
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECTALL);

			Deplacement deplacement = null;
			listDeplacements.clear();
			while (rs.next()) {
				
				deplacement = new Deplacement();
				
				try {
					deplacement.setNature(rs.getString("nature"));
				} catch (ParameterNullException e) {
					e.printStackTrace();
				}
				
				deplacement.setId(rs.getInt("idDeplacement"));
				deplacement.setActif(rs.getBoolean("actif"));
				
				listDeplacements.add(deplacement);
				
			}
		} catch (SQLException e) {
			throw new DALException("selectAll deplacement failed - ", e);
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
		return listDeplacements;
	}

	@Override
	public List<Deplacement> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Deplacement selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
