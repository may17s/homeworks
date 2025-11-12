package com.may.a03.controller;

import com.may.a03.dto.MedicalNoteDto;
import com.may.a03.service.MedicalNoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class MedicalNoteController {
    private final MedicalNoteService service;

    public MedicalNoteController(MedicalNoteService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public List<MedicalNoteDto> getAll() {
        return service.getAllActive();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<MedicalNoteDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public MedicalNoteDto create(@Valid @RequestBody MedicalNoteDto dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MedicalNoteDto> update(
            @PathVariable Long id,
            @RequestBody @Valid MedicalNoteDto dto) {
        MedicalNoteDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }
}