package com.ahmete.busbuscard.mapper;

import com.ahmete.busbuscard.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {
	CardMapper INTSTANCE = Mappers.getMapper(CardMapper.class);
	
	Card generateCard(final Card card);
}