package com.ahmete.busbuscard.entity;

import com.ahmete.busbuscard.utility.enums.ETransactionType;
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
@Table(name = "tbl_transaction")
public class Transaction extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(name = "trans_date")
	@Builder.Default
	LocalDate transDate = LocalDate.now();
	@Column(name ="card_id")
	Long cardId;
	Long amount;
	@Enumerated(EnumType.STRING)
	@Column(name="transaction_type")
	ETransactionType transactionType;
	
}