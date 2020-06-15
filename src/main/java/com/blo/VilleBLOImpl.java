package com.blo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO {

	@Autowired
	private VilleDAO villeDAO;
	
	public ArrayList<Ville> getInfoVille() {
		ArrayList<Ville> villes;
		
		villes = villeDAO.findAllVilles();
		return villes;
	}

	@Override
	public ArrayList<Ville> getSelectionVille(Ville ville) {
		ArrayList<Ville> villes;
		
		villes = villeDAO.findSomeVilles(ville);
		return villes;
	}

	@Override
	public boolean addAVille(Ville ville) {
		return villeDAO.addVille(ville);
	}

	@Override
	public boolean modifAVille(Ville ville) {
		return villeDAO.modifVille(ville);
	}

	@Override
	public boolean deleteAVille(Ville ville) {
		return villeDAO.deleteVille(ville);
	}

}
