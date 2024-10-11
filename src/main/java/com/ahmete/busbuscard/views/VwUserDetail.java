package com.ahmete.busbuscard.views;

import com.ahmete.busbuscard.utility.enums.ECardType;
import com.ahmete.busbuscard.utility.enums.EGender;
import com.ahmete.busbuscard.utility.enums.ETitle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@AllArgsConstructor
@Builder
@Data
public class VwUserDetail {
	
	String name;
	String surname;
	String tc;
	EGender gender;
	String address;
	ETitle titles;
	String uuid;
	Long balance;
	Long expiryDate;
	ECardType type;

	public LocalDate getExpiryDateInLocalDateFormat(){
		return Instant.ofEpochMilli(this.expiryDate)
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}
}