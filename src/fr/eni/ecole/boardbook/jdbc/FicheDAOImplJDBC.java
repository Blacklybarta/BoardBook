package fr.eni.ecole.boardbook.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.sun.org.apache.bcel.internal.util.SecuritySupport;

import fr.eni.ecole.boardbook.bo.Deplacement;
import fr.eni.ecole.boardbook.bo.Fiche;
import fr.eni.ecole.boardbook.bo.Lieu;
import fr.eni.ecole.boardbook.bo.Utilisateur;
import fr.eni.ecole.boardbook.bo.Vehicule;
import fr.eni.ecole.boardbook.bo.exception.ParameterNullException;
import fr.eni.ecole.boardbook.dal.DALException;
import fr.eni.ecole.boardbook.dal.DAO;
import fr.eni.ecole.boardbook.dal.DBConnection;

public class FicheDAOImplJDBC implements DAO<Fiche>{
	
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private List<Fiche> listFiches = new ArrayList<>();
	private List<Utilisateur> listUtilisateur = new ArrayList<>();
	

	private static final String SQL_INSERT_FICHE = "INSERT INTO FICHE(dateDepart,"
			+ "nbKmEntree,commentaire,cloture,idDeplacement,idLieuDepart,idVehicule,idLieuArrivee) "
			+ "VALUES(?,?,?,?,?,?,?,?)";
	
	private static final String SQL_INSERT_RENSEIGNER="INSERT INTO RENSEIGNER(idFiche,idUtilisateur) "
			+ "VALUES (?,?)";
	
	private static final String SQL_DELETE_RENSEIGNER="DELETE FROM RENSEIGNER WHERE idFiche=?";
	private static final String SQL_DELETE_FICHE="DELETE FROM FICHE WHERE idFiche=?";
	
	private static final String SQL_SELECT_BY_UTILISATEUR="SELECT *,LIEU_DEPART.nom AS lieu_depart,"
			+ "LIEU_ARRIVEE.nom AS lieu_arrivee FROM FICHE INNER JOIN RENSEIGNER "
			+"ON FICHE.idFiche = RENSEIGNER.idFiche INNER JOIN DEPLACEMENT "
			+"ON DEPLACEMENT.idDeplacement = FICHE.idDeplacement INNER JOIN LIEU AS LIEU_DEPART "
			+"ON LIEU_DEPART.idLieu = FICHE.idLieuDepart INNER JOIN LIEU AS LIEU_ARRIVEE "
			+"ON LIEU_ARRIVEE.idLieu = FICHE.idLieuArrivee INNER JOIN VEHICULE "
			+"ON VEHICULE.idVehicule = FICHE.idVehicule WHERE cloture=0 AND idUtilisateur=?";
	
	private static final String SQL_UPDATE ="UPDATE FICHE SET carburantNbLitre=?,carburantMontant=?,nbKmSortie=?,dateCloture=?,cloture=? WHERE idFiche=?";
	
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
			
			pstmt.setDate(1, new java.sql.Date(data.getDateDepart().getTimeInMillis()));
			pstmt.setInt(2, data.getNbKmEntree());
			pstmt.setString(3, data.getCommentaire());
			pstmt.setBoolean(4, false);
			
			// Id en lien sur les autres tables
			pstmt.setInt(5, data.getNatureDeplacement().getId());
			pstmt.setInt(6, data.getLieuDepart().getId());
			pstmt.setInt(7, data.getVehiculeLoue().getId());
			pstmt.setInt(8, data.getLieuArrivee().getId());
			
			int nbRows = pstmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					data.setId(rs.getInt(1));
				}
			}
			
			// Ajout dans la table RENSEIGNER
			pstmt = con.prepareStatement(SQL_INSERT_RENSEIGNER);
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
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE);
			pstmt.setDouble(1, data.getCarburantNbLitre());
			pstmt.setDouble(2, data.getCarburantMontant());
			pstmt.setInt(3, data.getNbKmSortie());
			pstmt.setDate(4, new java.sql.Date(data.getDateCloture().getTimeInMillis()));
			pstmt.setBoolean(5, data.isCloture());
			pstmt.setInt(6, data.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update fiche failed - " + data, e);
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
	
	public Fiche selectByUtilisateur(int id) throws DALException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Fiche fiche = null;
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL_SELECT_BY_UTILISATEUR);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				fiche = new Fiche();
				fiche.setId(rs.getInt("idFiche"));
				fiche.setCommentaire(rs.getString("commentaire"));
				fiche.setCloture(rs.getBoolean("cloture"));
				
				try {
					GregorianCalendar gcDateDepart = new GregorianCalendar();
					gcDateDepart.setTime(rs.getDate("dateDepart"));
					fiche.setDateDepart(gcDateDepart);
					
					Deplacement deplacement = new Deplacement();
					deplacement.setId(rs.getInt("idDeplacement"));
					deplacement.setNature(rs.getString("nature"));
					fiche.setNatureDeplacement(deplacement);
					
					Lieu lieuDepart = new Lieu();
					lieuDepart.setId(rs.getInt("idLieuDepart"));
					lieuDepart.setNom(rs.getString("lieu_depart"));
					fiche.setLieuDepart(lieuDepart);
					
					Lieu lieuArrivee = new Lieu();
					lieuArrivee.setId(rs.getInt("idLieuArrivee"));
					lieuArrivee.setNom(rs.getString("lieu_arrivee"));
					fiche.setLieuArrivee(lieuArrivee);
					
					Vehicule vehicule = new Vehicule();
					vehicule.setId(rs.getInt("idVehicule"));
					vehicule.setImmatriculation(rs.getString("immatriculation"));
					vehicule.setMarque(rs.getString("marque"));
					fiche.setVehiculeLoue(vehicule);
					
					fiche.setNbKmEntree(rs.getInt("nbKmEntree"));
					
				} catch (ParameterNullException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		} catch (SQLException e) {
			throw new DALException("selectByUtilisateur fiche failed - ", e);
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
		return fiche;
	}

}
