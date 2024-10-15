package com.ahmete.mapper;

import com.ahmete.busbuscard.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

//    @Mapping(source = "name", target = "name")
//    @Mapping(source = "surname", target = "surname")
//    @Mapping(source = "tc", target = "tc")
//    @Mapping(source = "gender", target = "gender")
//    User fromApplyCardRequestDto(final DenemeRequestDto dto);
}
