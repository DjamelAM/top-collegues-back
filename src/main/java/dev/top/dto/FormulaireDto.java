package dev.top.dto;

public class FormulaireDto {
	String matricule;
	String pseudo;
	String photo;

	public FormulaireDto(String matricule, String pseudo, String photo) {
		super();
		this.matricule = matricule;
		this.pseudo = pseudo;
		this.photo = photo;
	}

	public FormulaireDto() {
		super();
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
