package com.ahmete.busbuscard.views;

import com.ahmete.busbuscard.utility.enums.ECardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwCardDetail {
	String uuid;
	Long balance;
	ECardType type;
	LocalDate expiryDate;
}