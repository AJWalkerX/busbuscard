package com.ahmete.busbuscard.views;

import com.ahmete.busbuscard.utility.enums.ETransport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwPaymentDetail {
	Long amount;
	ETransport transport;
	LocalDate paymentDate;
}