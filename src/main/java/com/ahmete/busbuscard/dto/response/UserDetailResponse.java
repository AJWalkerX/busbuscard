package com.ahmete.busbuscard.dto.response;

import com.ahmete.busbuscard.utility.enums.ECardType;
import com.ahmete.busbuscard.utility.enums.EGender;
import com.ahmete.busbuscard.utility.enums.ETitle;
import com.ahmete.busbuscard.views.VwUserDetail;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class UserDetailResponse extends BaseResponse<VwUserDetail> {
	
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