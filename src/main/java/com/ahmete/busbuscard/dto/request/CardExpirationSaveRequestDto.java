package com.ahmete.busbuscard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CardExpirationSaveRequestDto {
    private Long cardId;
    private Long expirationDate;

    public long setExpirationDate(){
        long currentMilliSecs = System.currentTimeMillis();
        long oneYearInMillis = 365L * 24 * 60 * 60 * 1000;
        this.expirationDate = currentMilliSecs + oneYearInMillis;
        return expirationDate;
    }
}
