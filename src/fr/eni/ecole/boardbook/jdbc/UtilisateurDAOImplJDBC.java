package fr.eni.ecole.boardbook.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Fiche;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.dal.DAO;
import fr.eni.ecole.boardbook.dal.DBConnection;

public class UtilisateurDAOImplJDBC implements DAO<Utilisateur>{
	
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Utilisateur> listUtilisateurs = new ArrayList<>();
	
	private static final String SQL_SELECT_BY_IDENTIFIANT = "SELECT * FROM UTILISATEUR WHERE identifiant=? and mdp=?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM UTILISATEUR WHERE idUtilisateur=?";
	
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
	public void insert(Utilisateur data) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Utilisateur data) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utilisateur selectById(int id) throws SQLException {
		// TODO Auto-generated method stub
		con = null;
		pstmt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setId(rs.getInt("idUtilisateur"));
				utilisateur.setIdentifiant(rs.getString("identifiant"));
				utilisateur.setMdp(rs.getString("mdp"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
				utilisateur.setConducteur(rs.getBoolean("conducteur"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return utilisateur;
	}

	@Override
	public Utilisateur selectByIdentifiant(String identifiant, String mdp) throws SQLException {
		// TODO Auto-generated method stub
		con = null;
		pstmt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_IDENTIFIANT);
			pstmt.setString(1, identifiant);
			pstmt.setString(2, mdp);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setId(rs.getInt("idUtilisateur"));
				utilisateur.setIdentifiant(rs.getString("identifiant"));
				utilisateur.setMdp(rs.getString("mdp"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
				utilisateur.setConducteur(rs.getBoolean("conducteur"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return utilisateur;
	}

	@Override
	public List<Utilisateur> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> selectByKeyWord(String recherche) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
