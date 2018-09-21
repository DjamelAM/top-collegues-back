package dev.top.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.top.entities.Vote;
import dev.top.exception.InvalidVoteIdException;
import dev.top.exception.NoVoteException;
import dev.top.repos.VoteRepo;

@Service
public class VoteService {
	private VoteRepo voteRepo;

	public VoteService(VoteRepo voteRepo) {
		super();
		this.voteRepo = voteRepo;
	}

	public List<Vote> allVotesSince(Vote vote) {
		List<Vote> votes = new ArrayList<Vote>();
		this.voteRepo.findAll().forEach(vot -> {
			if (vot.getDateCreation().compareTo(vote.getDateCreation()) < 0) {
				votes.add(vot);
			}
		});
		if (votes.isEmpty()) {

			throw new NoVoteException();
		} else {
			return votes;
		}

	}

	public Vote findById(int id) throws InvalidVoteIdException {
		return this.voteRepo.findById(id).orElseThrow(() -> new InvalidVoteIdException());
	}

	public List<Vote> findAll() {
		return this.voteRepo.findAll();
	}

}
