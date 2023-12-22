package com.fatec.controller;

import com.fatec.service.CommonInfoService;
import com.fatec.dto.GetCommonInformationDTO;
import com.fatec.dto.InfoResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/information")
@RequiredArgsConstructor
@Tag(name = "info")
public class InfoController {
    private final CommonInfoService commonInfoProvider;

    @GetMapping()
    public ResponseEntity<InfoResponseDTO> getCommonInformation(GetCommonInformationDTO getCommonInformationDTO){
        return ResponseEntity.ok(commonInfoProvider.buildCommonInfo(getCommonInformationDTO));
    }
}
