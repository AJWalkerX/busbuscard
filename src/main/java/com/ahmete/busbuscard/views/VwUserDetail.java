package com.ahmete.busbuscard.views;

import com.ahmete.busbuscard.utility.enums.ECardType;
import com.ahmete.busbuscard.utility.enums.EGender;
import com.ahmete.busbuscard.utility.enums.ETitle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
	LocalDate expiryDate;
	ECardType type;
}