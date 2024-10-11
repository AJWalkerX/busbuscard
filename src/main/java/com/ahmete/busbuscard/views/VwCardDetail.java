package com.ahmete.busbuscard.views;

import com.ahmete.busbuscard.utility.enums.ECardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwCardDetail {
	String uuid;
	Long balance;
	ECardType type;
	Long expiryDate;

	public LocalDate getExpiryDateInLocalDateFormat(){
		return Instant.ofEpochMilli(this.expiryDate)
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}
}