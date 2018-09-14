package dev.top.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.top.entities.Collegue;
import dev.top.repos.CollegueRepo;

@RestController()
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
	public Collegue majCollegue(@PathVariable String nom, Integer points) {
		collegueRepo.findByName(nom).setPoints(points);
		return collegueRepo.findByName(nom);

	}
}
