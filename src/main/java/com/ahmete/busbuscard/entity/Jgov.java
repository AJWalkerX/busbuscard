package com.ahmete.busbuscard.entity;

import com.ahmete.busbuscard.utility.enums.ETitle;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_jgov")
public class Jgov extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(name = "user_id")
	Long userId;
	@Column(name = "card_id")
	Long cardId;
	String address;
	@Enumerated(EnumType.STRING)
	ETitle title;
}