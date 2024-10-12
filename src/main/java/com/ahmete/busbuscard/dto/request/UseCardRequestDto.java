package com.ahmete.busbuscard.dto.request;

import com.ahmete.busbuscard.utility.enums.ETransport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UseCardRequestDto {
	String card_uuid;
	ETransport eTransport;
}