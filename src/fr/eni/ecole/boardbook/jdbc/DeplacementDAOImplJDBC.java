package fr.eni.ecole.boardbook.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Deplacement;
import fr.eni.ecole.boardbook.dal.DAO;
import fr.eni.ecole.boardbook.dal.DBConnection;

public class DeplacementDAOImplJDBC implements DAO<Deplacement>{
	
	private static final String INSERT = "INSERT INTO Deplacement nature VALUES (?)";
	private static final String DELETE = "DELETE FROM Deplacement WHERE idDeplacement=?";
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Deplacement> listDeplacements = new ArrayList<>();
	
	@Override
	public void insert(Deplacement data) throws SQLException {
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, data.getNature());
			int nbRows = pstmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet key = pstmt.getGeneratedKeys();
				if (key.next()) {
					data.setId(key.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new SQLException("Erreur lors de l'ajout d'un déplacement");
		}finally {
			con.close();
		}
	}

	@Override
	public void update(Deplacement data) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws SQLException {
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Echec lors de la suppression du déplacement");
		}finally {
			con.close();
		}
		
	}

	@Override
	public Deplacement selectById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Deplacement selectByMail(String mail, String mdp) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deplacement> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deplacement> selectByKeyWord(String recherche) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
