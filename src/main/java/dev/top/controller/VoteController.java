package dev.top.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.top.entities.Vote;
import dev.top.exception.NoVoteException;
import dev.top.services.VoteService;

@RestController() // le responsebody inclut permet de traduire en json
@CrossOrigin
@RequestMapping("/votes")
public class VoteController {

	private VoteService voteService;

	public VoteController(VoteService voteService) {
		super();
		this.voteService = voteService;
	}

	@GetMapping("")
	public ResponseEntity<List<Vote>> findByVote(@RequestParam int since) {
		List<Vote> list = new ArrayList<Vote>();
		if (this.voteService.findAll().isEmpty()) {
			throw new NoVoteException();
		}
		if (since == 0) {
			// renvoie les 3 derniers votes
			list.add(this.voteService.findAll().get(this.voteService.findAll().size() - 1));
			list.add(this.voteService.findAll().get(this.voteService.findAll().size() - 2));
			list.add(this.voteService.findAll().get(this.voteService.findAll().size() - 3));
			return ResponseEntity.ok(list);

		} else {
			// renvoie tous les votes depuis ce vote
			return ResponseEntity.ok(this.voteService.allVotesSince(this.voteService.findById(since)));

		}

	}
}
