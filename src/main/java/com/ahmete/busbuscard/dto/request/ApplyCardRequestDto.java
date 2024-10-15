package com.ahmete.busbuscard.dto.request;

import com.ahmete.busbuscard.utility.enums.EGender;
import com.ahmete.busbuscard.utility.enums.ETitle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ApplyCardRequestDto {
    String address;
    @NotEmpty
    String titles;
    //User Entity
    @NotEmpty
    String name;
    @NotEmpty
    String surname;
    @NotNull
    @Size(min = 11, max = 11)
    String tc;
    @NotEmpty
    String gender;

}
