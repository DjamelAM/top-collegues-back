package dev.top;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.top.entities.Collegue;
import dev.top.repos.CollegueRepo;

@Component
public class StartupDataInit {

	CollegueRepo collegueRepo;

	public StartupDataInit(CollegueRepo collegueRepo) {
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
