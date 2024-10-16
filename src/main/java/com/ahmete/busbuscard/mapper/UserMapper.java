package com.ahmete.busbuscard.mapper;

import com.ahmete.busbuscard.dto.request.ApplyCardRequestDto;
import com.ahmete.busbuscard.dto.request.UpdateUserRequestDto;
import com.ahmete.busbuscard.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE/*,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE*/)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "tcNo", target = "tc")
    @Mapping(source = "firstname", target = "name")
    @Mapping(source = "lastname", target = "surname")
    User fromApplyCardRequestDto(final ApplyCardRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User fromUpdateUserRequestDto(final UpdateUserRequestDto dto, @MappingTarget User user);

}