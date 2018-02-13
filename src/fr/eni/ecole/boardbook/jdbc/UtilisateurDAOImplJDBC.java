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
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAO;
import fr.eni.ecole.boardbook.dal.DBConnection;

public class UtilisateurDAOImplJDBC implements DAO<Utilisateur>{
	
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Utilisateur> listUtilisateurs = new ArrayList<>();
	
	private static final String SQL_SELECT_BY_IDENTIFIANT = "SELECT * FROM UTILISATEUR WHERE identifiant=? and mdp=?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM UTILISATEUR WHERE idUtilisateur=?";
	private static final String SQL_INSERT = "INSERT INTO UTILISATEUR(identifiant,mdp,nom,prenom,administrateur,conducteur) VALUES(?,?,?,?,?,?)";
	private static final String SQL_SELECTALL = "SELECT * FROM UTILISATEUR";
	private static final String SQL_DELETE = "UPDATE UTILISATEUR SET administrateur=?,conducteur=? WHERE idUtilisateur=?";
	private static final String SQL_UPDATE = "UPDATE UTILISATEUR SET identifiant=?,mdp=?,nom=?,prenom=?,administrateur=?,conducteur=? WHERE idUtilisateur=?";
	
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
	public void insert(Utilisateur data) throws DALException {
		// TODO Auto-generated method stub
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, data.getIdentifiant());
			pstmt.setString(2, data.getMdp());
			pstmt.setString(3, data.getNom());
			pstmt.setString(4, data.getPrenom());
			pstmt.setBoolean(5, data.isAdministrateur());
			pstmt.setBoolean(6, data.isConducteur());

			int nbRows = pstmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					data.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Insert utilisateur failed - " + data, e);
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
	public void update(Utilisateur data) throws DALException {
		// TODO Auto-generated method stub
		con = null;
		pstmt = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, data.getIdentifiant());
			pstmt.setString(2, data.getMdp());
			pstmt.setString(3, data.getNom());
			pstmt.setString(4, data.getPrenom());
			pstmt.setBoolean(5, data.isAdministrateur());
			pstmt.setBoolean(6, data.isConducteur());
			pstmt.setInt(7, data.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update utilisateur failed - " + data, e);
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

	
	/**
	 * On ne supprime pas un utilisateur, on lui retire son droit d'administrateur 
	 * et son droit de conducteur
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
			pstmt.setBoolean(2, false);
			pstmt.setInt(3, id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("delete utilisateur failed - " + e);
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
	public Utilisateur selectById(int id) throws DALException {
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
	public Utilisateur selectByIdentifiant(String identifiant, String mdp) throws DALException {
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
	public List<Utilisateur> selectAll() throws DALException {
		// TODO Auto-generated method stub
		con = null;
		stmt = null;
		ResultSet rs = null;
		listUtilisateurs = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_SELECTALL);
			Utilisateur utilisateur = null;

			while (rs.next()) {
				
				utilisateur = new Utilisateur();
				utilisateur.setId(rs.getInt("idUtilisateur"));
				utilisateur.setIdentifiant(rs.getString("identifiant"));
				
				try {
					utilisateur.setMdp(rs.getString("mdp"));
				} catch (ParameterNullException e) {
					e.printStackTrace();
				}
				try {
					utilisateur.setNom(rs.getString("nom"));
				} catch (ParameterNullException e) {
					e.printStackTrace();
				}
				try {
					utilisateur.setPrenom(rs.getString("prenom"));					
				} catch (ParameterNullException e) {
					e.printStackTrace();
				}
				
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
				utilisateur.setConducteur(rs.getBoolean("conducteur"));

				listUtilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - ", e);
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
		return listUtilisateurs;
	}

	@Override
	public List<Utilisateur> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
