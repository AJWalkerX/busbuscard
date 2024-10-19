package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.dto.request.PersonnelTransportRequestDto;
import com.ahmete.busbuscard.entity.PersonnelCard;
import com.ahmete.busbuscard.entity.PersonnelTransportLog;
import com.ahmete.busbuscard.exception.BusbusCardException;
import com.ahmete.busbuscard.exception.EErrorType;
import com.ahmete.busbuscard.mapper.PersonnelTransportMapper;
import com.ahmete.busbuscard.repository.PersonnelTransportRepository;
import com.ahmete.busbuscard.utility.enums.EPersonnelType;
import com.ahmete.busbuscard.utility.enums.ETransportType;
import com.ahmete.busbuscard.views.VwPersonnel;
import com.ahmete.busbuscard.views.VwTransport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PersonnelTransportService {
    private final PersonnelTransportRepository personnelTransportRepository;
    private final TransportService transportService;
    private final PersonnelService  personnelService;
    private final PersonnelCardService personnelCardService;

    public String startShift(PersonnelTransportRequestDto dto) {
        VwTransport vwTransport = transportService.getTransportVw(dto.plate());
        VwPersonnel vwPersonnel = personnelService.getPersonnelVw(dto.PCUuid());
        if (vwTransport.getETransportType().equals(ETransportType.BUS) &&
                vwPersonnel.getEPersonnelType().equals(EPersonnelType.DRIVER)) {
            PersonnelTransportLog personnelTransportLog = PersonnelTransportMapper.INSTANCE.fromPersonnelTransportLogRequestDto(dto);
            personnelTransportLog.setStartShift(System.currentTimeMillis());
            transportService.updateTransportStatusToActive(vwTransport.getId());
            personnelCardService.updateCardStatusToActive(dto.PCUuid());
            personnelTransportRepository.save(personnelTransportLog);
            return "Brumm Brumm";
        }
        if (vwTransport.getETransportType().equals(ETransportType.HIGHWAY) &&
                vwPersonnel.getEPersonnelType().equals(EPersonnelType.PILOT)) {
            PersonnelTransportLog personnelTransportLog = PersonnelTransportMapper.INSTANCE.fromPersonnelTransportLogRequestDto(dto);
            personnelTransportLog.setStartShift(System.currentTimeMillis());
            transportService.updateTransportStatusToActive(vwTransport.getId());
            personnelCardService.updateCardStatusToActive(dto.PCUuid());
            personnelTransportRepository.save(personnelTransportLog);
            return "fiyuuuuuuuuuuuuuuuuuu";
        }
        if (vwTransport.getETransportType().equals(ETransportType.SUBWAY) &&
                vwPersonnel.getEPersonnelType().equals(EPersonnelType.MACHINIST)) {
            PersonnelTransportLog personnelTransportLog = PersonnelTransportMapper.INSTANCE.fromPersonnelTransportLogRequestDto(dto);
            personnelTransportLog.setStartShift(System.currentTimeMillis());
            transportService.updateTransportStatusToActive(vwTransport.getId());
            personnelCardService.updateCardStatusToActive(dto.PCUuid());
            personnelTransportRepository.save(personnelTransportLog);
            return "cuff cuff cuff";
        }
        if (vwTransport.getETransportType().equals(ETransportType.FERRY) &&
                vwPersonnel.getEPersonnelType().equals(EPersonnelType.CAPITAN)) {
            PersonnelTransportLog personnelTransportLog = PersonnelTransportMapper.INSTANCE.fromPersonnelTransportLogRequestDto(dto);
            personnelTransportLog.setStartShift(System.currentTimeMillis());
            transportService.updateTransportStatusToActive(vwTransport.getId());
            personnelCardService.updateCardStatusToActive(dto.PCUuid());
            personnelTransportRepository.save(personnelTransportLog);
            return "glu glu glu";
        }
        throw new BusbusCardException(EErrorType.WRONG_PERSONNEL_CARD_ERROR);
    }

    public String endShift(PersonnelTransportRequestDto dto) {
        Long transportId = transportService.findIdByPlate(dto.plate());
        Long personnelCardId = personnelCardService.findCardIdByUuid(dto.PCUuid());
        Optional<PersonnelTransportLog> personnelTransportLogOpt
                = personnelTransportRepository.findLastPersonnelTransportLog(transportId, personnelCardId);
        if (personnelTransportLogOpt.isEmpty()){
            throw new BusbusCardException(EErrorType.NO_SUCH_SHIFT_FOUND_ERROR);
        }
        transportService.updateTransportStatusToPassive(transportId);
        personnelCardService.updateCardStatusToPassive(dto.PCUuid());
        PersonnelTransportLog personnelTransportLog = personnelTransportLogOpt.get();
        personnelTransportLog.setEndShift(System.currentTimeMillis());
        personnelTransportRepository.save(personnelTransportLog);
        return "Shift Ended!";
    }
}
