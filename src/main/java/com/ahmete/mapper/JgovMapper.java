package com.ahmete.mapper;

import com.ahmete.busbuscard.dto.request.ApplyCardRequestDto;
import com.ahmete.busbuscard.entity.Jgov;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JgovMapper {
    JgovMapper INSTANCE = Mappers.getMapper(JgovMapper.class);

//    Jgov fromApplyCardRequestDto(ApplyCardRequestDto dto);
}
