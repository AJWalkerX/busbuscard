package com.ahmete.busbuscard.mapper;

import com.ahmete.busbuscard.dto.request.PersonnelTransportRequestDto;
import com.ahmete.busbuscard.entity.PersonnelTransportLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonnelTransportMapper {
    PersonnelTransportMapper INSTANCE = Mappers.getMapper(PersonnelTransportMapper.class);

    @Mapping(source = "PCUuid", target = "personnelCardId")
    PersonnelTransportLog fromPersonnelTransportLogRequestDto(final PersonnelTransportRequestDto dto);
}
