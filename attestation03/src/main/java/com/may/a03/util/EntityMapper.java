package com.may.a03.util;

import com.may.a03.dto.MedicalNoteDto;
import com.may.a03.dto.PatientDto;
import com.may.a03.model.MedicalNote;
import com.may.a03.model.Patient;
import com.may.a03.model.Disease;
import com.may.a03.repository.PatientRepository;
import com.may.a03.repository.DiseaseRepository;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {
    private final PatientRepository patientRepo;
    private final DiseaseRepository diseaseRepo;

    public EntityMapper(PatientRepository patientRepo, DiseaseRepository diseaseRepo) {
        this.patientRepo = patientRepo;
        this.diseaseRepo = diseaseRepo;
    }

    // MedicalNote
    public MedicalNote toEntity(MedicalNoteDto dto) {
        MedicalNote note = new MedicalNote();
        note.setPatient(patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Пациент не найден")));
        note.setDisease(diseaseRepo.findById(dto.getDiseaseId())
                .orElseThrow(() -> new RuntimeException("Заболевание не найдено")));
        note.setNoteDate(dto.getNoteDate());
        note.setNote(dto.getNote());
        return note;
    }

    public MedicalNoteDto toDto(MedicalNote entity) {
        MedicalNoteDto dto = new MedicalNoteDto();
        dto.setId(entity.getId());
        dto.setPatientId(entity.getPatient().getId());
        dto.setDiseaseId(entity.getDisease().getId());
        dto.setNoteDate(entity.getNoteDate());
        dto.setNote(entity.getNote());
        return dto;
    }

    // Patient
    public Patient toEntity(PatientDto dto) {
        Patient entity = dto.getId() != null ?
                patientRepo.findById(dto.getId()).orElse(new Patient()) :
                new Patient();
        entity.setFullName(dto.getFullName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setGender(dto.getGender());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());

        return entity;
    }

    public PatientDto toDto(Patient entity) {
        PatientDto dto = new PatientDto();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setBirthDate(entity.getBirthDate());
        dto.setGender(entity.getGender());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        return dto;
    }

}