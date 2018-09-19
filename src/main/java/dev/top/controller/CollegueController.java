package dev.top.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.top.Converters;
import dev.top.dto.AvisDto;
import dev.top.dto.CollegueDto;
import dev.top.entities.Collegue;
import dev.top.services.CollegueService;

@RestController() // le responsebody inclut permet de traduire en json
@CrossOrigin
@RequestMapping("/collegues")
public class CollegueController {
	private CollegueService collegueService;

	public CollegueController(CollegueService collegueService) {
		super();
		this.collegueService = collegueService;
	}

	@GetMapping
	public ResponseEntity<List<CollegueDto>> findAll() {
		return ResponseEntity.ok(this.collegueService.listerCollegues().stream()
				.map(col -> Converters.COLLEGUE_TO_COLLEGUE_DTO.convert(col)).collect(Collectors.toList()));
	}

	@GetMapping("/{nom}")
	public ResponseEntity<CollegueDto> findByName(@PathVariable String nom) {
		return ResponseEntity.ok(Converters.COLLEGUE_TO_COLLEGUE_DTO.convert(this.collegueService.findByName(nom)));
	}

	@PatchMapping("/{nom}")
	public ResponseEntity<CollegueDto> patch(@PathVariable String nom, @RequestBody AvisDto avis) {

		Collegue collegueModifie = this.collegueService.modifierScore(nom, avis.getAction());

		return ResponseEntity.ok(Converters.COLLEGUE_TO_COLLEGUE_DTO.convert(collegueModifie));
	}

}
