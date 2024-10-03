package com.ahmete.busbuscard.entity;

import com.ahmete.busbuscard.utility.enums.EType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_card")
public class Card extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	UUID uuid;
	Long balance;
	@Column(name="expiry_date")
	LocalDate expiryDate;
	@Enumerated(EnumType.STRING)
	EType type;
}