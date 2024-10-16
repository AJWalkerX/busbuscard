package com.ahmete.busbuscard.mapper;

import com.ahmete.busbuscard.dto.request.UseCardRequestDto;
import com.ahmete.busbuscard.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {
	
	PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);
	
	Payment fromUseCardRequestDto(final UseCardRequestDto dto);
}