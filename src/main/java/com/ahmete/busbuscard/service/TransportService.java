package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.Transport;
import com.ahmete.busbuscard.exception.BusbusCardException;
import com.ahmete.busbuscard.exception.EErrorType;
import com.ahmete.busbuscard.repository.TransportRepository;
import com.ahmete.busbuscard.utility.enums.ETransportState;
import com.ahmete.busbuscard.utility.enums.ETransportType;
import com.ahmete.busbuscard.views.VwTransport;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
				Transport transport=Transport.builder().eTransportType(ETransportType.BUS).eTransportState(ETransportState.PASSIVE).build();
				transportRepository.save(transport);
			}
			else if (counter < 10){
				Transport transport=Transport.builder().eTransportType(ETransportType.FERRY).eTransportState(ETransportState.PASSIVE).build();
				transportRepository.save(transport);
			}
			else if (counter < 15){
				Transport transport=Transport.builder().eTransportType(ETransportType.SUBWAY).eTransportState(ETransportState.PASSIVE).build();
				transportRepository.save(transport);
			}
			counter++;
		}
		return true;
	}

    public VwTransport getTransportVw(String plate) {
		Optional<VwTransport> vwTransport = transportRepository.findByPlateNo(plate);
		if (vwTransport.isEmpty()) {
			throw  new BusbusCardException(EErrorType.TRANSPORT_NOT_FOUND_ERROR);
		}
		return vwTransport.get();
    }
}