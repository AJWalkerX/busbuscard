package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.dto.request.PersonnelTransportRequestDto;
import com.ahmete.busbuscard.dto.response.BaseResponse;
import com.ahmete.busbuscard.entity.Transport;
import com.ahmete.busbuscard.exception.BusbusCardException;
import com.ahmete.busbuscard.exception.EErrorType;
import com.ahmete.busbuscard.repository.PersonnelTransportRepository;
import com.ahmete.busbuscard.utility.enums.EPersonnelType;
import com.ahmete.busbuscard.utility.enums.ETransportType;
import com.ahmete.busbuscard.views.VwPersonnel;
import com.ahmete.busbuscard.views.VwTransport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonnelTransportService {
    private final PersonnelTransportRepository personnelTransportRepository;
    private final TransportService transportService;
    private final PersonnelService  personnelService;


    public String startShift(PersonnelTransportRequestDto dto) {
        VwTransport vwTransport = transportService.getTransportVw(dto.plate());
        VwPersonnel vwPersonnel = personnelService.getPersonnelVw(dto.PCUuid());
        if (vwTransport.getETransportType().equals(ETransportType.BUS) &&
                vwPersonnel.getEPersonnelType().equals(EPersonnelType.DRIVER)) {
            return "Brumm Brumm";
        }
        if (vwTransport.getETransportType().equals(ETransportType.HIGHWAY) &&
                vwPersonnel.getEPersonnelType().equals(EPersonnelType.PILOT)) {
            return "fiyuuuuuuuuuuuuuuuuuu";
        }
        if (vwTransport.getETransportType().equals(ETransportType.SUBWAY) &&
                vwPersonnel.getEPersonnelType().equals(EPersonnelType.MACHINIST)) {
            return "cuff cuff cuff";
        }
        if (vwTransport.getETransportType().equals(ETransportType.FERRY) &&
                vwPersonnel.getEPersonnelType().equals(EPersonnelType.CAPITAN)) {
            return "glu glu glu";
        }
        throw new BusbusCardException(EErrorType.WRONG_PERSONNEL_CARD_ERROR);
    }
}
