package dev.top.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.top.entities.Collegue;
import dev.top.repos.CollegueRepo;

@RestController()
@CrossOrigin
@RequestMapping("/collegues")
public class CollegueController {
	CollegueRepo collegueRepo;

	public CollegueController(CollegueRepo collegueRepo) {
		super();
		this.collegueRepo = collegueRepo;
	}

	@GetMapping
	public List<Collegue> findAll() {
		return this.collegueRepo.findAll();

	}

	@PatchMapping("/{nom}")
	public Collegue majCollegue(@PathVariable String nom, @RequestBody String action) {
		Collegue newCollegue = collegueRepo.findByName(nom);
		int cible = collegueRepo.findByName(nom).getPoints();
		if (StringUtils.isNotEmpty(nom) && action.contains("AIMER") && cible <= 990) {
			newCollegue.setPoints(cible + 10);
		} else if (StringUtils.isNotEmpty(nom) && action.contains("AIMER") && cible == 995) {
			newCollegue.setPoints(cible + 5);
		}
		if (StringUtils.isNotEmpty(nom) && action.contains("DETESTER") && cible > -1000) {
			newCollegue.setPoints(cible - 5);
		}
		collegueRepo.save(newCollegue);
		return collegueRepo.findByName(nom);
	}
}
