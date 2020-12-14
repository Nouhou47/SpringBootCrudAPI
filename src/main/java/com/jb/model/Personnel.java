package com.jb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "personnel")
public class Personnel {
	
	@Id
	private String id;
	private String fonction;
	private String departement;
	
	public Personnel(String id, String fonction, String departement) {
		super();
		this.id = id;
		this.fonction = fonction;
		this.departement = departement;
	}

	public Personnel() {
	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}
	
	@Override
	public String toString() {
		return "Personnel [id=" + id + ", fonction=" + fonction + ", departement=" + departement + "]";
	}
	
	
	
	
}
