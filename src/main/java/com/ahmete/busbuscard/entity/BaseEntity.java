package com.ahmete.busbuscard.entity;

import com.ahmete.busbuscard.utility.PlateGeneratable;
import com.ahmete.busbuscard.utility.UuidGeneratable;
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
import java.util.UUID;

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
		createAt = LocalDate.now();
		updateAt = LocalDate.now();
		
		if (this instanceof UuidGeneratable) {
			UuidGeneratable uuidGeneratable = (UuidGeneratable) this;
			if (uuidGeneratable.getUuid() == null) {
				UUID uuidHash = UUID.randomUUID();
				uuidGeneratable.setUuid(uuidHash.toString().replace("-", "").substring(0, 16));
			}
		}
		if (this instanceof PlateGeneratable) {
			PlateGeneratable plateGeneratable = (PlateGeneratable) this;
			if (((Transport) this).getPlateNo() == null) {
				((Transport) this).setPlateNo(plateGeneratable.generateRandomPlate());
			}
		}
		
	}
	
	@PreUpdate
	protected void update(){
		updateAt=LocalDate.now();
	}
}