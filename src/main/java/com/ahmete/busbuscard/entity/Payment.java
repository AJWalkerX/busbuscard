package com.ahmete.busbuscard.entity;

import com.ahmete.busbuscard.utility.enums.ETransportType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
	Long paymentDate = System.currentTimeMillis();
	@Enumerated(EnumType.STRING)
    ETransportType transport;
}