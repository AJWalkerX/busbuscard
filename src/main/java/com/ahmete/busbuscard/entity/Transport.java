package com.ahmete.busbuscard.entity;

import com.ahmete.busbuscard.utility.PlateGeneratable;
import com.ahmete.busbuscard.utility.enums.ETransport;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Random;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_transport")
public class Transport extends BaseEntity implements PlateGeneratable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Enumerated(EnumType.STRING)
	ETransport eTransport;
	
	//TODO uuid gibi otomatik generatelenecek
	String plateNo;
	
	@Override
	public String generateRandomPlate() {
		String cityCode = "34";
		char letter = 'B';
		Random random = new Random();
		
		int number = 1000 + random.nextInt(9000);
		
		return cityCode + " " + letter + " " + number;
	}
	
	
	
}