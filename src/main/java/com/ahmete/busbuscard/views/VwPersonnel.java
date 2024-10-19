package com.ahmete.busbuscard.views;

import com.ahmete.busbuscard.utility.enums.ECardStatus;
import com.ahmete.busbuscard.utility.enums.EPersonnelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwPersonnel {
    private Long cardId;
    private EPersonnelType ePersonnelType;
}
