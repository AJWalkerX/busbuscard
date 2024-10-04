package com.ahmete.busbuscard.entity;

import com.ahmete.busbuscard.utility.enums.ETransport;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_payment")
public class Payment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(name="card_id")
	Long cardId;
	Long amount;
	@Column(name="payment_date")
	@Builder.Default
	LocalDate paymentDate = LocalDate.now();
	@Enumerated(EnumType.STRING)
	
	ETransport transport;
}