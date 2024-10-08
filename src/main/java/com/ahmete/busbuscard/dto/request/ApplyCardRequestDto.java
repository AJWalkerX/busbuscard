package com.ahmete.busbuscard.dto.request;

import com.ahmete.busbuscard.utility.enums.EGender;
import com.ahmete.busbuscard.utility.enums.ETitle;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ApplyCardRequestDto {
    String address;
    @NotNull
    @NotEmpty
    ETitle titles;
    //User Entity
    @NotNull
    @Size(min = 3, max = 20)
    String name;
    @NotNull
    @Size(min = 3, max = 20)
    String surname;
    @NotNull
    @Size(min = 11, max = 11)
    String tc;
    EGender gender;
}
