package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
//@RequestMapping("/path")
class VilleController {

	@Autowired
	VilleBLO villeBLOService;

	// Methode GET
	@RequestMapping(value = "/villeOld", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> appelGet() {
		System.out.println("Appel GET (old)");

		ArrayList<Ville> villes = villeBLOService.getInfoVille();

		return villes;
	}

	// Methode GETBis
	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> appelGetBis(
			@RequestParam(required = false, value = "codeCommune") String codeCommune,
			@RequestParam(required = false, value = "nom_commune") String nom_commune,
			@RequestParam(required = false, value = "codePostal") String codePostal,
			@RequestParam(required = false, value = "latitude") String latitude,
			@RequestParam(required = false, value = "longitude") String longitude,
			@RequestParam(required = false, value = "libelle_acheminement") String libelle_acheminement,
			@RequestParam(required = false, value = "ligne_5") String ligne_5) {

		System.out.println("Appel GETBis");
		
		Ville ville = new Ville();
		ville.setCodeCommune(codeCommune);
		ville.setNomCommune(nom_commune);
		ville.setCodePostal(codePostal);
		ville.setLatitude(latitude);
		ville.setLongitude(longitude);
		ville.setLibelleAcheminement(libelle_acheminement);
		ville.setLigne_5(ligne_5);
		
		
		ArrayList<Ville> villes = villeBLOService.getSelectionVille(ville);

		return villes;
	}
	
	// Methode POST
		@RequestMapping(value = "/ville", method = RequestMethod.POST)
		@ResponseBody
		public String postVille(
				@RequestParam(required = false, value = "codeCommune") String codeCommune,
				@RequestParam(required = false, value = "nom_commune") String nom_commune,
				@RequestParam(required = false, value = "codePostal") String codePostal,
				@RequestParam(required = false, value = "latitude") String latitude,
				@RequestParam(required = false, value = "longitude") String longitude,
				@RequestParam(required = false, value = "libelle_acheminement") String libelle_acheminement,
				@RequestParam(required = false, value = "ligne_5") String ligne_5) {
			
			System.out.println("Appel POST");
			
			Ville ville = new Ville();
			ville.setCodeCommune(codeCommune);
			ville.setNomCommune(nom_commune);
			ville.setCodePostal(codePostal);
			ville.setLatitude(latitude);
			ville.setLongitude(longitude);
			ville.setLibelleAcheminement(libelle_acheminement);
			ville.setLigne_5(ligne_5);
			
			
			if(villeBLOService.addAVille(ville)) {
				return "Ajout effectué avec succès";
			}
			else {
				return "Echec de l'ajout";
			}
		}
		
		// Methode PUT
		@RequestMapping(value = "/ville", method = RequestMethod.PUT)
		@ResponseBody
		public String putVille(
				@RequestParam(required = false, value = "codeCommune") String codeCommune,
				@RequestParam(required = false, value = "nom_commune") String nom_commune,
				@RequestParam(required = false, value = "codePostal") String codePostal,
				@RequestParam(required = false, value = "latitude") String latitude,
				@RequestParam(required = false, value = "longitude") String longitude,
				@RequestParam(required = false, value = "libelle_acheminement") String libelle_acheminement,
				@RequestParam(required = false, value = "ligne_5") String ligne_5) {
			
			System.out.println("Appel PUT");
			
			Ville ville = new Ville();
			ville.setCodeCommune(codeCommune);
			ville.setNomCommune(nom_commune);
			ville.setCodePostal(codePostal);
			ville.setLatitude(latitude);
			ville.setLongitude(longitude);
			ville.setLibelleAcheminement(libelle_acheminement);
			ville.setLigne_5(ligne_5);
			
			
			if(villeBLOService.modifAVille(ville)) {
				return "Modification effectué avec succès";
			}
			else {
				return "Echec de la modification";
			}
		}
		
		// Methode DELETE
		@RequestMapping(value = "/ville", method = RequestMethod.DELETE)
		@ResponseBody
		public String deleteVille(
				@RequestParam(required = false, value = "codeCommune") String codeCommune,
				@RequestParam(required = false, value = "nom_commune") String nom_commune,
				@RequestParam(required = false, value = "codePostal") String codePostal,
				@RequestParam(required = false, value = "latitude") String latitude,
				@RequestParam(required = false, value = "longitude") String longitude,
				@RequestParam(required = false, value = "libelle_acheminement") String libelle_acheminement,
				@RequestParam(required = false, value = "ligne_5") String ligne_5) {
			
			System.out.println("Appel DELETE");
			
			Ville ville = new Ville();
			ville.setCodeCommune(codeCommune);
			ville.setNomCommune(nom_commune);
			ville.setCodePostal(codePostal);
			ville.setLatitude(latitude);
			ville.setLongitude(longitude);
			ville.setLibelleAcheminement(libelle_acheminement);
			ville.setLigne_5(ligne_5);
			
			
			if(villeBLOService.deleteAVille(ville)) {
				return "Suppression effectué avec succès";
			}
			else {
				return "Echec de la suppresion";
			}
		}
}
