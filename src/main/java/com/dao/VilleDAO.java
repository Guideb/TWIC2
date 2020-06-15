package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {
	
	public ArrayList<Ville> findAllVilles();
	
	public ArrayList<Ville> findSomeVilles(Ville ville);
	
	public boolean addVille(Ville ville);
	//

}
