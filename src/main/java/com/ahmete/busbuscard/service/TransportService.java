package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.Transport;
import com.ahmete.busbuscard.repository.TransportRepository;
import com.ahmete.busbuscard.utility.enums.ETransport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {
	private final TransportRepository transportRepository;
	public TransportService(TransportRepository transportRepository) {
		this.transportRepository = transportRepository;
	}
	
	public String generatePlate() {
		
		Transport transport=Transport.builder().eTransport(ETransport.BUS).build();
		Transport transport1=Transport.builder().eTransport(ETransport.FERRY).build();
		Transport transport2=Transport.builder().eTransport(ETransport.HIGHWAY).build();
		Transport transport3 =Transport.builder().eTransport(ETransport.SUBWAY).build();
		transportRepository.saveAll(List.of(transport,transport1,transport2,transport3));
		return "Plaka oluşturuldu";
	}
}