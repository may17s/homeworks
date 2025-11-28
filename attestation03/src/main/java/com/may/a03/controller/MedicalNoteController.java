package com.may.a03.controller;

import com.may.a03.dto.MedicalNoteDto;
import com.may.a03.service.MedicalNoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Медицинские записи", description = "Операции с медицинскими записями")
@RestController
@RequestMapping("/api/notes")
public class MedicalNoteController {
    private final MedicalNoteService service;

    public MedicalNoteController(MedicalNoteService service) {
        this.service = service;
    }

    @Operation(summary = "Получение списка всех активных медицинских записей")
    @GetMapping
    @ResponseBody
    public List<MedicalNoteDto> getAll() {
        return service.getAllActive();
    }

    @Operation(summary = "Получение конкретной медицинской записи")
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<MedicalNoteDto> getById(@Parameter(
            description = "ID медицинской записи",
            required = true) @PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(description = "Объект медицинская запись", summary = "Создание новой медицинской записи")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Медицинская запись успешно создана")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public MedicalNoteDto create(@RequestBody @Valid MedicalNoteDto dto) {
        return service.create(dto);
    }

    @Operation(summary = "Удаление конкретной медицинской записи")
    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Медицинская запись успешно удалена")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@Parameter(
            description = "ID удаляемой медицинской записи",
            required = true) @PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Объект медицинская запись, которая должен быть обновлёна",
            summary = "Изменение конкретной медицинской записи")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MedicalNoteDto> update(@Parameter(
            description = "ID медицинской записи",
            required = true) @PathVariable Long id,
            @RequestBody @Valid MedicalNoteDto dto) {
        MedicalNoteDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }
}