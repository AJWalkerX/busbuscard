package com.ahmete.busbuscard.controller;

import com.ahmete.busbuscard.dto.response.BaseResponse;
import com.ahmete.busbuscard.entity.BaseEntity;
import com.ahmete.busbuscard.service.PersonnelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ahmete.busbuscard.constans.RestApi.*;

@RestController
@RequestMapping(PERSONNEL)
@RequiredArgsConstructor
public class PersonnelController {
    private final PersonnelService personnelService;

    @GetMapping(GENERATE)
    public ResponseEntity<BaseResponse<Boolean>> generatePersonnelAndCard() {
        personnelService.generatePersonnelAndCard();
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .success(true)
                .code(200)
                .message("Personnel generated")
                .success(true)
                .data(true)
                .build());
    }
}
