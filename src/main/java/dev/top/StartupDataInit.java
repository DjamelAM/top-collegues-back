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

			this.collegueRepo.save(new Collegue("Francis", 100,
					"https://cdn.freebiesupply.com/logos/large/2x/travis-ci-monochrome-logo-png-transparent.png",
					"Francisator", null, null, null));
			this.collegueRepo.save(new Collegue("Miguel", 200,
					"https://cdn.freebiesupply.com/logos/large/2x/travis-ci-monochrome-logo-png-transparent.png",
					"xxMiguel34xx", null, null, null));
			this.collegueRepo.save(new Collegue("Jeanne", -1000,
					"https://cdn.freebiesupply.com/logos/large/2x/travis-ci-monochrome-logo-png-transparent.png",
					"Jeannator", null, null, null));
			this.collegueRepo.save(new Collegue("NomAngela", 990,
					"https://cdn.freebiesupply.com/logos/large/2x/travis-ci-monochrome-logo-png-transparent.png",
					"Angelaaaaa", null, null, "Angela"));
		}

	}
}
