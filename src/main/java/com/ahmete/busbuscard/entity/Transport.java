package com.ahmete.busbuscard.entity;

import com.ahmete.busbuscard.utility.PlateGeneratable;
import com.ahmete.busbuscard.utility.enums.ETransportState;
import com.ahmete.busbuscard.utility.enums.ETransportType;
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
	ETransportType eTransportType;
	@Enumerated(EnumType.STRING)
	ETransportState eTransportState;
	@Column(nullable = false, unique = true)
	String plateNo;
	@Column(name = "current_stop")
	String currentStop;
	
	@Override
	public String generateRandomPlate() {
		String cityCode = "34";
		char letter = 'B'; // Belediyenin B'si
		Random random = new Random();
		
		int number = 1000 + random.nextInt(9000);
		
		return cityCode + " " + letter + " " + number;
	}
	
	
	
}