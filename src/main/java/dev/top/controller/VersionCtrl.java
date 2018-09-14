package dev.top.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.top.entities.Version;
import dev.top.repos.VersionRepo;

@RestController()
@RequestMapping("/versions")
public class VersionCtrl {

	private VersionRepo versionRepo;

	public VersionCtrl(VersionRepo versionRepo) {
		super();
		this.versionRepo = versionRepo;
	}

	@GetMapping
	public List<Version> findAll() {
		return this.versionRepo.findAll();
	}
}
