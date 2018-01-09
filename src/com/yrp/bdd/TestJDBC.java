package com.yrp.bdd;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class TestJDBC {
	/* La liste qui contiendra tous les résultats de nos essais */
	private List<String> messages = new ArrayList<String>();
	
	public List<String> executerTests(HttpServletRequest request){
		/* Liste de tests a effectuer */
		
		return messages;
	}
}
