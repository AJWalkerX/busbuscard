package com.ahmete.busbuscard.entity;

import com.ahmete.busbuscard.utility.UuidGeneratable;
import com.ahmete.busbuscard.utility.enums.ECardStatus;
import com.ahmete.busbuscard.utility.enums.ECardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_personel_card")
public class PersonnelCard extends BaseEntity implements UuidGeneratable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(updatable = false, nullable = false, unique = true)
	String uuid;
	@Enumerated(EnumType.STRING)
	@Builder.Default
	ECardType cardType=ECardType.PERSONNEL;

	@Builder.Default
	ECardStatus eCardStatus = ECardStatus.RESTING;
}