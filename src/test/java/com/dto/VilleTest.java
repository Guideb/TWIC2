package com.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VilleTest {
	
	@Test
	public void testConstructeurVide() throws Exception {
		final Ville ville = new Ville();
		final String resultatAttendu = null;
		final String resultatTrouve = ville.getCodeCommune();
		assertEquals("ConstructeurVide non vide", resultatAttendu, resultatTrouve);
	}

	@Test
	public void testConstructeurPlein() throws Exception {
		final String StringAttendu = "TEST";
		final Ville ville = new Ville();
		ville.setCodeCommune(StringAttendu);
		ville.setCodePostal(StringAttendu);
		ville.setLatitude(StringAttendu);
		ville.setLibelleAcheminement(StringAttendu);
		ville.setLigne_5(StringAttendu);
		ville.setLongitude(StringAttendu);
		ville.setNomCommune(StringAttendu);
		
		
		final String resultatAttendu = StringAttendu;
		
		
		String resultatTrouve = ville.getCodeCommune();
		assertEquals("Mauvais CodeCommune", resultatAttendu, resultatTrouve);
		
		resultatTrouve = ville.getCodePostal();
		assertEquals("Mauvais CodePostal", resultatAttendu, resultatTrouve);
		
		resultatTrouve = ville.getLatitude();
		assertEquals("Mauvais Latitude", resultatAttendu, resultatTrouve);
		
		resultatTrouve = ville.getLibelleAcheminement();
		assertEquals("Mauvais LibelleAcheminement", resultatAttendu, resultatTrouve);
		
		resultatTrouve = ville.getLigne_5();
		assertEquals("Mauvais Ligne_5", resultatAttendu, resultatTrouve);
		
		resultatTrouve = ville.getLongitude();
		assertEquals("Mauvais Longitude", resultatAttendu, resultatTrouve);
		
		resultatTrouve = ville.getNomCommune();
		assertEquals("Mauvais NomCommune", resultatAttendu, resultatTrouve);
	}
}
