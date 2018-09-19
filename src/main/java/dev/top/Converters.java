package dev.top;

import org.springframework.core.convert.converter.Converter;

import dev.top.dto.CollegueDto;
import dev.top.entities.Collegue;

public interface Converters {

	Converter<Collegue, CollegueDto> COLLEGUE_TO_COLLEGUE_DTO = source -> {
		CollegueDto collegueDto = new CollegueDto();

		collegueDto.setName(source.getName());
		collegueDto.setPoints(source.getPoints());
		collegueDto.setUrl(source.getUrl());

		return collegueDto;
	};

	Converter<CollegueDto, Collegue> COLLEGUE_DTO_TO_COLLEGUE = source -> {
		Collegue collegue = new Collegue();

		collegue.setName(source.getName());
		collegue.setPoints(source.getPoints());
		collegue.setUrl(source.getUrl());

		return collegue;
	};

}