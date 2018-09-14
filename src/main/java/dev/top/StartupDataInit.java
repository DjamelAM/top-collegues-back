package dev.top;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.top.entities.Version;
import dev.top.repos.VersionRepo;

@Component
public class StartupDataInit {

	private VersionRepo versionRepo;

	public StartupDataInit(VersionRepo versionRepo) {
		super();
		this.versionRepo = versionRepo;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void init() {

		if (this.versionRepo.count() <= 0) {
			this.versionRepo.save(new Version("v1"));
			this.versionRepo.save(new Version("v2"));
			this.versionRepo.save(new Version("v3"));
			this.versionRepo.save(new Version("v4"));
		}

	}
}
