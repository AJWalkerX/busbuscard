package com.ahmete.busbuscard.entity;

import com.ahmete.busbuscard.utility.enums.EGender;
import com.ahmete.busbuscard.utility.enums.EPersonnelType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_personnel")
public class Personnel extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	String surname;
	String phone;
	@Column(unique = true, nullable = false)
	@Size(min = 11, max = 11)
	String tc;
	@Column(name="personnel_card_id")
	String personnelCardId;
	
	@Enumerated(EnumType.STRING)
	EGender gender;
	@Enumerated(EnumType.STRING)
	@Column(name ="personnel_type")
	EPersonnelType ePersonnelType;
}