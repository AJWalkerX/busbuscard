package com.ahmete.busbuscard.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record ApplyCardRequestDto(
        String address,
        @NotEmpty
        String title,
        //User Entity
        @NotEmpty
        String firstname,
        @NotEmpty
        String lastname,
        @NotNull
        @Size(min = 11, max = 11)
        String tcNo,
        @NotEmpty
        String gender

) {

}
