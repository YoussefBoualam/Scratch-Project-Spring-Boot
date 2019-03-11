package com.teste.voiture.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "voiture")
@Table(name="voiture")
public class Voiture implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "marque")
	private String marque;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prix")
	private float prix;
	@Column(name = "photo")
	private String photo;
	
	public Voiture() {
		super();
	}
	public Voiture(String marque, String nom, float prix,String photo) {
		super();
		this.marque = marque;
		this.nom = nom;
		this.prix = prix;
		this.photo=photo;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getInfo() {
		return "Voiture [id=" + id + ", marque=" + marque + ", nom=" + nom + ", prix=" + prix +", photo=" + photo + "]";
	}
	
	

}
