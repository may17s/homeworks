package com.may.a03.service;

import com.may.a03.dto.MedicalNoteDto;
import com.may.a03.dto.PatientDto;
import com.may.a03.model.MedicalNote;
import com.may.a03.repository.MedicalNoteRepository;
import com.may.a03.util.EntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicalNoteService {

    private final MedicalNoteRepository repository;
    private final EntityMapper mapper;

    public MedicalNoteService(MedicalNoteRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<MedicalNoteDto> getAllActive() {
        return repository.findByDeletedFalse().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public MedicalNoteDto findById(Long id) {
        MedicalNote note = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Запись не найдена"));
        return mapper.toDto(note);
    }

    public MedicalNoteDto create(MedicalNoteDto dto) {
        MedicalNote entity = mapper.toEntity(dto);
        MedicalNote saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public void softDelete(Long id) {
        MedicalNote record = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Запись не найдена"));
        record.setDeleted(true);
        repository.save(record);
    }

    public MedicalNoteDto update(Long id, MedicalNoteDto dto) {
        MedicalNote existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Запись с номером : " + id + " не найдена"));

        MedicalNote updated = mapper.toEntity(dto);
        updated.setId(existing.getId());

        MedicalNote saved = repository.save(updated);
        return mapper.toDto(saved);
    }

}