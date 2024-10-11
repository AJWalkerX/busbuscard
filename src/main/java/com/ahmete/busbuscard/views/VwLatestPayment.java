package com.ahmete.busbuscard.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwLatestPayment {
    private Long paymentDate;
    private Long amount;
}
