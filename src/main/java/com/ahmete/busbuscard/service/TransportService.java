package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.Transport;
import com.ahmete.busbuscard.repository.TransportRepository;
import com.ahmete.busbuscard.utility.enums.ETransportState;
import com.ahmete.busbuscard.utility.enums.ETransportType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {
	private final TransportRepository transportRepository;
	public TransportService(TransportRepository transportRepository) {
		this.transportRepository = transportRepository;
	}

	public Boolean generateTransport() {
		int counter = 0;

		while (counter < 16){
			if (counter < 5){
				Transport transport=Transport.builder().eTransport(ETransportType.BUS).eTransportState(ETransportState.PASSIVE).build();
				transportRepository.save(transport);
			}
			else if (counter < 10){
				Transport transport=Transport.builder().eTransport(ETransportType.FERRY).eTransportState(ETransportState.PASSIVE).build();
				transportRepository.save(transport);
			}
			else if (counter < 15){
				Transport transport=Transport.builder().eTransport(ETransportType.SUBWAY).eTransportState(ETransportState.PASSIVE).build();
				transportRepository.save(transport);
			}
			counter++;
		}
		return true;
	}
}