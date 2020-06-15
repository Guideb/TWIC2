package com.blo;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleBLO {

	public ArrayList<Ville> getInfoVille();
	
	public ArrayList<Ville> getSelectionVille(Ville ville);
	
	public boolean addAVille(Ville ville);
	
	public boolean modifAVille(Ville ville);
	
	public boolean deleteAVille(Ville ville);
	
}
