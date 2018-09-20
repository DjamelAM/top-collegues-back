package dev.top.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Collegue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String prenom;

	private Integer points;

	private String url;
	private String pseudo;
	private String adresse;
	private String email;

	public Collegue() {
		super();
	}

	public Collegue(String name, Integer points, String url, String pseudo, String adresse, String email,
			String prenom) {
		super();
		this.name = name;
		this.points = points;
		this.url = url;
		this.pseudo = pseudo;
		this.adresse = adresse;
		this.email = email;
		this.prenom = prenom;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Collegue [id=" + id + ", name=" + name + ", points=" + points + ", url=" + url + ", pseudo=" + pseudo
				+ ", adresse=" + adresse + ", email=" + email + "]";
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}