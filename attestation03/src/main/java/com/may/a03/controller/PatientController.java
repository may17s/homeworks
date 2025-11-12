package com.may.a03.controller;

import com.may.a03.dto.MedicalNoteDto;
import com.may.a03.dto.PatientDto;
import com.may.a03.service.MedicalNoteService;
import com.may.a03.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public List<PatientDto> getAll() {
        return service.getAllPatients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PatientDto create(@RequestBody PatientDto dto) {
        return service.create(dto);
    }
}
