package com.ahmete.busbuscard.views;

import com.ahmete.busbuscard.utility.enums.ETransportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@AllArgsConstructor
@Builder
@Data
public class VwPaymentDetail {
	Long amount;
	ETransportType transport;
	Long paymentDate;

	public LocalDateTime getPaymentLocalDate(Long paymentDate) {
		return Instant.ofEpochMilli(paymentDate).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
}