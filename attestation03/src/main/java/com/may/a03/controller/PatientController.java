package com.may.a03.controller;

import com.may.a03.dto.PatientDto;
import com.may.a03.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Пациенты", description = "Операции с пациентами")
@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @Operation(summary = "Получение списка всех пациентов", tags = "Пациенты")
    @GetMapping
    @ResponseBody
    public List<PatientDto> getAll() {
        return service.getAllPatients();
    }

    @Operation(description="Объект пациент", summary = "Создание новой записи о пациенте", tags = "Пациенты")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Новая запись о пациенте успешно создана")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PatientDto create(@RequestBody
                @Valid PatientDto dto) {
        return service.create(dto);
    }
}
