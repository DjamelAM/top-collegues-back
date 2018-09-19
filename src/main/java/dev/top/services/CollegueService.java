package dev.top.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.top.entities.Avis;
import dev.top.entities.Collegue;
import dev.top.exception.InvalidPseudoException;
import dev.top.exception.ServiceException;
import dev.top.repos.CollegueRepo;

@Service
public class CollegueService {

	private CollegueRepo collRepo;

	public CollegueService(CollegueRepo collRepo) {
		super();
		this.collRepo = collRepo;
	}

	public List<Collegue> listerCollegues() {
		return this.collRepo.findAll();
	}

	/**
	 * 
	 * @param pseudo
	 * @param avisUtilisateur
	 * @return
	 * @throws ServiceException
	 * @throws PseudoInvalideException
	 */
	public Collegue modifierScore(String pseudo, Avis avisUtilisateur) throws ServiceException, InvalidPseudoException {

		return this.collRepo.findByName(pseudo).map(collegueTrouve -> {
			int cible = collegueTrouve.getPoints();
			if (Avis.AIMER.equals(avisUtilisateur) && cible <= 990) {
				collegueTrouve.setPoints(cible + 10);
			} else if (Avis.AIMER.equals(avisUtilisateur) && cible == 995) {
				collegueTrouve.setPoints(cible + 5);
			}
			if (Avis.DETESTER.equals(avisUtilisateur) && cible > -1000) {
				collegueTrouve.setPoints(cible - 5);
			}

			this.collRepo.save(collegueTrouve);
			return collegueTrouve;
		}).orElseThrow(() -> new InvalidPseudoException());

	}

	public Collegue findByName(String pseudo) throws ServiceException, InvalidPseudoException {

		return this.collRepo.findByName(pseudo).orElseThrow(() -> new InvalidPseudoException());

	}

}
