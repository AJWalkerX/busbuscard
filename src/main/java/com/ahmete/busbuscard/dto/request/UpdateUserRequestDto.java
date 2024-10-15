package com.ahmete.busbuscard.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

public record UpdateUserRequestDto(
        @Pattern(regexp = "^.{11}$", message = "Tc kimlik 11 haneli olmalıdır!")
        String tc,
        @NotEmpty
        String name,
        @NotEmpty
        String surname
) {
}
