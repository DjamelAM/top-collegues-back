package dev.top.controller;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
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

	@EventListener(ContextRefreshedEvent.class)
	public void init() {

		if (this.collegueRepo.count() <= 0) {
			this.collegueRepo.save(new Collegue("Francis", 100));
			this.collegueRepo.save(new Collegue("Miguel", 200));
			this.collegueRepo.save(new Collegue("Jeanne", 10));
			this.collegueRepo.save(new Collegue("Angela", 500));
		}

	}
}
