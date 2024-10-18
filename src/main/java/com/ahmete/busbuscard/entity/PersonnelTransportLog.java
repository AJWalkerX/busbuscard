package com.ahmete.busbuscard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_personel_transport_log")
public class PersonnelTransportLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long transportId;
	Long personnelCardId;
}