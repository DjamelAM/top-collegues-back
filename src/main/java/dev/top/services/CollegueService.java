package dev.top.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.top.entities.Avis;
import dev.top.entities.Collegue;
import dev.top.entities.CollegueApi;
import dev.top.exception.InvalidMatriculeException;
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

		return this.collRepo.findByPseudo(pseudo).map(collegueTrouve -> {
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

	public Collegue findByPseudo(String pseudo) throws ServiceException, InvalidPseudoException {

		return this.collRepo.findByPseudo(pseudo).orElseThrow(() -> new InvalidPseudoException());

	}

	public Collegue findByMatricule(String matricule) throws ServiceException, InvalidMatriculeException {
		RestTemplate restTemplate = new RestTemplate();
		CollegueApi[] collegues = restTemplate.getForObject(
				"http://collegues-api.cleverapps.io/collegues?matricule=" + matricule, CollegueApi[].class);

		if (collegues.length == 0) {
			throw new InvalidMatriculeException();
		}
		Collegue collegue = new Collegue();
		collegue.setUrl(collegues[0].getPhoto());
		collegue.setAdresse(collegues[0].getAdresse());
		collegue.setEmail(collegues[0].getEmail());
		collegue.setName(collegues[0].getNom());
		collegue.setPoints(0);
		collegue.setPrenom(collegues[0].getPrenom());
		return collegue;

	}

	public boolean existCollegue(Collegue collegue) {

		if (collRepo.findAll().contains(collegue)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean existsByPseudo(String pseudo) {
		if (collRepo.findAll().contains(collRepo.findByPseudo(pseudo))) {
			return true;
		} else {
			return false;
		}

	}

	public String addCollegue(Collegue collegue) {
		if (this.collRepo.existsByName(collegue.getName())) {
			return "Collegue déjà existant";
		} else if (this.collRepo.existsByPseudo(collegue.getPseudo())) {
			return "Pseudo déjà existant";
		} else {
			this.collRepo.save(collegue);
			return "Collegue Enregistré";
		}

	}

}
