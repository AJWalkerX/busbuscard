package com.ahmete.busbuscard.entity;

import com.ahmete.busbuscard.utility.enums.ECardType;
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
	@Column(updatable = false, nullable = false, unique = true)
	String uuid;
	Long balance;
	@Enumerated(EnumType.STRING)
	ECardType type;

	@PrePersist
	public void generateUUID() {
		if (uuid == null) {
			UUID uuidHash = UUID.randomUUID();
			uuid = uuidHash.toString().replace("-", "").substring(0,16);
		}
	}
}