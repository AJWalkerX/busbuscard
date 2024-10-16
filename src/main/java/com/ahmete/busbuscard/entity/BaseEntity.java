package com.ahmete.busbuscard.entity;

import com.ahmete.busbuscard.utility.enums.EState;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public class BaseEntity {
	@Builder.Default
	EState state=EState.ACTIVE;
	LocalDate createAt;
	LocalDate updateAt;
	
	@PrePersist
	protected void create(){
		createAt=LocalDate.now();
		updateAt=LocalDate.now();
	}
	
	@PreUpdate
	protected void update(){
		updateAt=LocalDate.now();
	}
}