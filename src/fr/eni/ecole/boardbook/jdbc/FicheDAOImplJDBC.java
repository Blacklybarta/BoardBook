package fr.eni.ecole.boardbook.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.boardbook.bo.Deplacement;
import fr.eni.ecole.boardbook.bo.Fiche;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAO;
import fr.eni.ecole.boardbook.dal.DBConnection;

public class FicheDAOImplJDBC implements DAO<Fiche>{
	
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Fiche> listFiches = new ArrayList<>();
	private List<Utilisateur> listUtilisateur = new ArrayList<>();
	

	private static final String SQL_INSERT_FICHE = "INSERT INTO FICHE(dateDepart,carburantNbLitre,"
			+ "carburantMontant,nbKmEntree,nbKmSortie,commentaire,dateCloture,"
			+ "cloture,idDeplacement,idLieuDepart,idVehicule,idLieuArrivee) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_INSERT_RENSEIGNER="INSERT INTO RENSEIGNER(idFiche,idUtilisateur) "
			+ "VALUES (?,?)";
	
	private static final String SQL_DELETE_RENSEIGNER="DELETE FROM RENSEIGNER WHERE idFiche=?";
	private static final String SQL_DELETE_FICHE="DELETE FROM FICHE WHERE idFiche=?";
	
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
	public void insert(Fiche data) throws DALException {
		// TODO Auto-generated method stub
		try {
			// Ajout dans la table FICHE
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_FICHE, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setDate(1, new Date(data.getDateDepart().getTimeInMillis()));
			pstmt.setDouble(2, data.getCarburantNbLitre());
			pstmt.setDouble(3, data.getCarburantMontant());
			pstmt.setInt(4, data.getNbKmEntree());
			pstmt.setInt(5, data.getNbKmSortie());
			pstmt.setString(6, data.getCommentaire());
			pstmt.setDate(7, new Date(data.getDateCloture().getTimeInMillis()));
			pstmt.setBoolean(8, data.isCloture());
			
			// Id en lien sur les autres tables
			pstmt.setInt(9, data.getNatureDeplacement().getId());
			pstmt.setInt(10, data.getLieuDepart().getId());
			pstmt.setInt(11, data.getVehiculeLoue().getId());
			pstmt.setInt(12, data.getLieuArrivee().getId());
			
			int nbRows = pstmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					data.setId(rs.getInt(1));
				}
			}
			
			// Ajout dans la table RENSEIGNER
			pstmt = con.prepareStatement(SQL_INSERT_FICHE);
			// Recuperation des conducteurs
			listUtilisateur = data.getConducteurList();
			
			for (Utilisateur utilisateur : listUtilisateur) {
				pstmt.setInt(1, data.getId());
				pstmt.setInt(2, utilisateur.getId());
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			throw new DALException("Insert fiche failed - " + data, e);
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
	public void update(Fiche data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		try {
			con = DBConnection.getConnection();
			// 1°) Suppression dans la table RENSEIGNER
			pstmt = con.prepareStatement(SQL_DELETE_RENSEIGNER);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(SQL_DELETE_FICHE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("delete fiche failed - " + e);
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
	public Fiche selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Fiche> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fiche> selectByKeyWord(String recherche) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fiche selectByIdentifiant(String identifiant, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
