package com.ahmete.busbuscard.controller;

import com.ahmete.busbuscard.dto.request.PersonnelTransportRequestDto;
import com.ahmete.busbuscard.dto.response.BaseResponse;
import com.ahmete.busbuscard.entity.BaseEntity;
import com.ahmete.busbuscard.entity.PersonnelTransportLog;
import com.ahmete.busbuscard.exception.BusbusCardException;
import com.ahmete.busbuscard.exception.EErrorType;
import com.ahmete.busbuscard.service.PersonnelTransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ahmete.busbuscard.constans.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PERSONNEL_TRANSPORT)
public class PersonnelTransportController {

    private final PersonnelTransportService personnelTransportService;

    @PostMapping(USE_CARD)
    public ResponseEntity<BaseResponse<String>> startShift(PersonnelTransportRequestDto dto) {
        if (!dto.PCUuid().contains("PC")){
            //TODO: Buradaki kart hatası banka kart hatasına ait! Değiştirilirse daha iyi olur!
            throw new BusbusCardException(EErrorType.INVALID_CARD_ERROR);
        }
        return ResponseEntity.ok(BaseResponse.<String>builder()
                .message("Engine started")
                .code(200)
                .data(personnelTransportService.startShift(dto))
                .success(true)
                .build());
    }

    @PostMapping(USE_CARD_END)
    public ResponseEntity<BaseResponse<String>> endShift(PersonnelTransportRequestDto dto) {
        return ResponseEntity.ok(BaseResponse.<String>builder()
                .message("Engine started")
                .code(200)
                .data(personnelTransportService.endShift(dto))
                .success(true)
                .build());
    }

}
