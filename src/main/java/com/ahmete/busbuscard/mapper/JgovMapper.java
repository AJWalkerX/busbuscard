package com.ahmete.busbuscard.mapper;

import com.ahmete.busbuscard.dto.request.ApplyCardRequestDto;
import com.ahmete.busbuscard.dto.request.UpdateUserRequestDto;
import com.ahmete.busbuscard.entity.Jgov;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JgovMapper {
    JgovMapper INSTANCE = Mappers.getMapper(JgovMapper.class);

    @Mapping(source = "title", target = "titles")
    Jgov fromApplyCardRequestDto(ApplyCardRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Jgov fromUpdateUserRequestDto(UpdateUserRequestDto dto, @MappingTarget Jgov jgov);
}