package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.stereotype.Repository;

import com.config.JDBCConfiguration;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {

	private static Logger logger = Logger.getLogger(VilleDAOImpl.class.getName());

	private static final String[] ATTRIBUTS_VILLE = { "Code_commune_INSEE", "Nom_commune", "Code_postal", "Latitude",
			"Longitude", "Libelle_acheminement", "Ligne_5" };

	@SuppressWarnings("deprecation")
	public ArrayList<Ville> findAllVilles() {
		ArrayList<Ville> villes = new ArrayList<Ville>();

		String requete = "SELECT * FROM ville_france";
		Connection con = JDBCConfiguration.getConnection();

		try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(requete);) {
			while (rs.next()) {
				Ville ville = new Ville();
				ville.setCodeCommune(rs.getString("code_commune_insee"));
				ville.setNomCommune(rs.getString("nom_commune"));
				ville.setCodePostal(rs.getString("code_postal"));
				ville.setLatitude(rs.getString("latitude"));
				ville.setLongitude(rs.getString("longitude"));
				ville.setLibelleAcheminement(rs.getString("libelle_acheminement"));
				ville.setLigne_5(rs.getString("ligne_5"));
				villes.add(ville);
			}
		} catch (SQLException e) {
			logger.log(Priority.ERROR, "Error, couldn't get ville.");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.log(Priority.ERROR, "Error, couldn't close con1.");
			}
		}
		return villes;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ArrayList<Ville> findSomeVilles(Ville ville) {
		ArrayList<Ville> villes = new ArrayList<Ville>();

		String requete = "SELECT * FROM ville_france";
		String requeteWhere = " WHERE ";
		Connection con = JDBCConfiguration.getConnection();
		String[][] attributs = { ATTRIBUTS_VILLE,
				{ ville.getCodeCommune(), ville.getNomCommune(), ville.getCodePostal(), ville.getLatitude(),
						ville.getLongitude(), ville.getLibelleAcheminement(), ville.getLigne_5() } };

		// Creation String condition
		boolean conditionSQL = false;
		for (int i = 0; i < attributs[0].length; i++) {
			if (attributs[1][i] != null) {
				if (conditionSQL) {
					requeteWhere += "AND ";
				}
				requeteWhere += attributs[0][i] + "='" + attributs[1][i] + "' ";
				conditionSQL = true;
			}
		}

		// Assemblage des Strings requete
		String requeteFinale = requete;
		if (conditionSQL) {
			requeteFinale += requeteWhere;
		}

		try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(requeteFinale);) {
			while (rs.next()) {
				Ville ville1 = new Ville();
				ville1.setCodeCommune(rs.getString("code_commune_insee"));
				ville1.setNomCommune(rs.getString("nom_commune"));
				ville1.setCodePostal(rs.getString("code_postal"));
				ville1.setLatitude(rs.getString("latitude"));
				ville1.setLongitude(rs.getString("longitude"));
				ville1.setLibelleAcheminement(rs.getString("libelle_acheminement"));
				ville1.setLigne_5(rs.getString("ligne_5"));
				villes.add(ville1);
			}
		} catch (SQLException e) {
			logger.log(Priority.ERROR, "Error, couldn't getBis ville.");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.log(Priority.ERROR, "Error, couldn't close con2.");
			}
		}
		return villes;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean addVille(Ville ville) {

		String requete = "INSERT INTO ville_france (";
		Connection con = JDBCConfiguration.getConnection();
		boolean booleanRequete = false;

		for (String attribut : ATTRIBUTS_VILLE) {
			requete += "" + attribut + ",";
		}
		requete = requete.substring(0, requete.length() - 1);
		requete += ") VALUES (";

		if (ville.getCodeCommune() != null) {
			requete += "'" + ville.getCodeCommune() + "',";
		} else {
			requete += "'NULL" + Math.round(10000 * Math.random()) + "',"; // En tant que clef primaire on ne peut pas
																			// donner la même valeur à chaque fois.
		}

		requete += checkNull(ville.getNomCommune());
		requete += checkNull(ville.getCodePostal());
		requete += checkNull(ville.getLatitude());
		requete += checkNull(ville.getLongitude());
		requete += checkNull(ville.getLibelleAcheminement());
		requete += checkNull(ville.getLigne_5());

		requete = requete.substring(0, requete.length() - 1);
		requete += ")";

		try (Statement stmt = con.createStatement();) {
			stmt.executeUpdate(requete);
			booleanRequete = true;
		} catch (SQLException e) {
			booleanRequete = false;
			logger.log(Priority.ERROR, "Error, couldn't add ville.");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.log(Priority.ERROR, "Error, couldn't close con3.");
			}
		}
		return booleanRequete;
	}

	private String checkNull(String string) {
		if (string != null) {
			return "'" + string + "',";
		} else {
			return "'NULL',";
		}

	}

	// Modifie la ville dont le numero de commune est donné dans la ville en
	// parametre et lui donnes les autres parametres donnés dans la ville.

	@SuppressWarnings("deprecation")
	@Override
	public boolean modifVille(Ville ville) {
		boolean booleanRequete = false;
		String requete = "UPDATE ville_france SET ";
		String requeteWhere = " WHERE Code_commune_INSEE = '" + ville.getCodeCommune() + "'";

		String[][] attributs = { ATTRIBUTS_VILLE,
				{ ville.getCodeCommune(), ville.getNomCommune(), ville.getCodePostal(), ville.getLatitude(),
						ville.getLongitude(), ville.getLibelleAcheminement(), ville.getLigne_5() } };

		Connection con = JDBCConfiguration.getConnection();

		boolean conditionSQL = false;
		for (int i = 1; i < attributs[0].length; i++) {
			if (attributs[1][i] != null) {
				if (conditionSQL) {
					requete += ", ";
				}
				requete += attributs[0][i] + "='" + attributs[1][i] + "'";
				conditionSQL = true;
			}
		}
		try (Statement stmt = con.createStatement();) {
			stmt.executeUpdate(requete + requeteWhere);
			booleanRequete = true;
		} catch (SQLException e) {
			booleanRequete = false;
			logger.log(Priority.ERROR, "Error, couldn't modify ville.");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.log(Priority.ERROR, "Error, couldn't close con4.");
			}
		}
		return booleanRequete;
	}

	// DELETE toutes les villes dont les caracteristiques correspondent à celle
	// donnée en paramètre
	@SuppressWarnings("deprecation")
	@Override
	public boolean deleteVille(Ville ville) {
		boolean booleanRequete = false;
		String requete = "DELETE FROM ville_france";
		String requeteWhere = " WHERE ";

		String[][] attributs = { ATTRIBUTS_VILLE,
				{ ville.getCodeCommune(), ville.getNomCommune(), ville.getCodePostal(), ville.getLatitude(),
						ville.getLongitude(), ville.getLibelleAcheminement(), ville.getLigne_5() } };

		Connection con = JDBCConfiguration.getConnection();

		// Creation String condition
		boolean conditionSQL = false;
		for (int i = 0; i < attributs[0].length; i++) {
			if (attributs[1][i] != null) {
				if (conditionSQL) {
					requeteWhere += "AND ";
				}
				requeteWhere += attributs[0][i] + "='" + attributs[1][i] + "' ";
				conditionSQL = true;
			}
		}

		// Assemblage des Strings requete
		String requeteFinale = requete;
		if (conditionSQL) {
			requeteFinale += requeteWhere;
		}

		try (Statement stmt = con.createStatement();) {
			stmt.executeUpdate(requeteFinale);
			booleanRequete = true;
		} catch (SQLException e) {
			booleanRequete = false;
			logger.log(Priority.ERROR, "Error, couldn't delete ville.");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.log(Priority.ERROR, "Error, couldn't close con5.");
			}
		}
		return booleanRequete;
	}

}
