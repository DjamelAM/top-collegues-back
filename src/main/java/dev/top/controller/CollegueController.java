package dev.top.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.top.Converters;
import dev.top.dto.AvisDto;
import dev.top.dto.CollegueDto;
import dev.top.dto.FormulaireDto;
import dev.top.entities.Collegue;
import dev.top.exception.InvalidMatriculeException;
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

	@GetMapping("/{pseudo}")
	public ResponseEntity<CollegueDto> findByPseudo(@PathVariable String pseudo) {
		return ResponseEntity
				.ok(Converters.COLLEGUE_TO_COLLEGUE_DTO.convert(this.collegueService.findByPseudo(pseudo)));
	}

	@PatchMapping("/{pseudo}")
	public ResponseEntity<CollegueDto> patch(@PathVariable String pseudo, @RequestBody AvisDto avis) {

		Collegue collegueModifie = this.collegueService.modifierScore(pseudo, avis.getAction());

		return ResponseEntity.ok(Converters.COLLEGUE_TO_COLLEGUE_DTO.convert(collegueModifie));
	}

	@PostMapping("/new")
	public ResponseEntity<String> post(@RequestBody FormulaireDto form) {
		Collegue collegueAjouter = this.collegueService.findByMatricule(form.getMatricule());

		if (this.collegueService.existsByPseudo(form.getPseudo())) {
			throw new InvalidMatriculeException();
		} else {
			collegueAjouter.setPseudo(form.getPseudo());
		}

		if (StringUtils.isEmpty(form.getPhoto())) {
			collegueAjouter.setUrl(this.collegueService.findByMatricule(form.getMatricule()).getUrl());
		} else {
			collegueAjouter.setUrl(form.getPhoto());
		}
		System.out.println(collegueAjouter);
		return ResponseEntity.ok(this.collegueService.addCollegue(collegueAjouter));

	}

}
