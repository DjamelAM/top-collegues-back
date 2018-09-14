package dev.top.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
}
