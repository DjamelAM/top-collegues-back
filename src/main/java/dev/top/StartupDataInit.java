package dev.top;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.top.entities.Avis;
import dev.top.entities.Collegue;
import dev.top.entities.Vote;
import dev.top.repos.CollegueRepo;
import dev.top.repos.VoteRepo;

@Component
public class StartupDataInit {

	CollegueRepo collegueRepo;
	VoteRepo voteRepo;

	public StartupDataInit(CollegueRepo collegueRepo, VoteRepo voteRepo) {
		super();
		this.collegueRepo = collegueRepo;
		this.voteRepo = voteRepo;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		Collegue c1 = new Collegue("Francis", 100,
				"https://cdn.freebiesupply.com/logos/large/2x/travis-ci-monochrome-logo-png-transparent.png",
				"Francisator", null, null, null);
		if (this.collegueRepo.count() <= 0) {

			this.collegueRepo.save(c1);
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

		if (this.voteRepo.count() <= 0) {
			this.voteRepo.save(new Vote(Avis.AIMER,
					c1,
					null, 100));
		}

	}
}
