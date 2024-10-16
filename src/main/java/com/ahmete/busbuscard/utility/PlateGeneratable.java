package com.ahmete.busbuscard.utility;

import java.util.Random;

public interface PlateGeneratable {
	String generateRandomPlate();  // Plaka oluşturma metodu
	String getPlateNo();              // Plaka al
	void setPlateNo(String plate);

}