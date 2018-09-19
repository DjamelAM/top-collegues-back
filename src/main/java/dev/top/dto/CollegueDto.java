package dev.top.dto;

public class CollegueDto {
	private String name;

	private Integer points;

	private String url;

	public CollegueDto(String name, Integer points, String url) {
		super();
		this.name = name;
		this.points = points;
		this.url = url;
	}

	public CollegueDto() {
		super();
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

}
