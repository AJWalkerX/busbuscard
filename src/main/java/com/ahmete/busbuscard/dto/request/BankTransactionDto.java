package com.ahmete.busbuscard.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BankTransactionDto(
        @NotNull
        Long amount,
        @NotNull
        String uuid,
        @NotNull
        @Pattern(regexp = "^.{16}$", message = "ID must be exactly 16 characters long.")
        String cardNumber,
        @NotNull
        @Size(min = 3, max = 3)
        String cvc,
        @NotNull
        String fullName,
        @NotNull
        Integer endYear,
        @NotNull
        Integer endMonth

) {
}
