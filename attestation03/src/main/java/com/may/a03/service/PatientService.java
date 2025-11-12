package com.may.a03.service;

import com.may.a03.dto.MedicalNoteDto;
import com.may.a03.dto.PatientDto;
import com.may.a03.model.MedicalNote;
import com.may.a03.model.Patient;
import com.may.a03.repository.PatientRepository;
import com.may.a03.util.EntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientService {

    private final PatientRepository repository;
    private final EntityMapper mapper;

    public PatientService(PatientRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PatientDto> getAllPatients() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientDto create(PatientDto dto) {
        Patient entity = mapper.toEntity(dto);
        Patient saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public PatientDto findById(Long id) {
        Patient note = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Запись не найдена"));
        return mapper.toDto(note);
    }

}