package dev.top.controller;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;

import dev.top.dto.ErrorCode;
import dev.top.dto.ErrorDto;
import dev.top.exception.InvalidMatriculeException;
import dev.top.exception.InvalidPseudoException;
import dev.top.exception.InvalidVoteIdException;
import dev.top.exception.NoVoteException;

@ControllerAdvice
public class ServiceExceptionCtrl {

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<?> serviceException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.SERVICE, "Erreur côté service"));
	}

	@ExceptionHandler(InvalidPseudoException.class)
	public ResponseEntity<?> pseudoException() {
		return ResponseEntity.badRequest()
				.body(new ErrorDto(ErrorCode.PSEUDO_INVALID, "Le pseudo n'a pas été trouvé en base de données"));
	}

	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<?> invalidFormatException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.JSON_PARSE,
				"Erreur dans la conversion Java <> JSON (vérifier vos paramètres d'entrée)"));
	}

	@ExceptionHandler(InvalidMatriculeException.class)
	public ResponseEntity<?> matriculeException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.MATRICULE_INVALID, "Le matricule n'existe pas"));
	}

	@ExceptionHandler(InvalidVoteIdException.class)
	public ResponseEntity<?> voteIdException() {
		return ResponseEntity.badRequest().body(new ErrorDto(ErrorCode.ID_VOTE_INVALID, "Le vote n'existe pas"));
	}

	@ExceptionHandler(NoVoteException.class)
	public ResponseEntity<?> noVoteException() {
		return ResponseEntity.badRequest()
				.body(new ErrorDto(ErrorCode.AUCUN_VOTE, "Il n'y a pas de votes dans la bdd"));
	}
}