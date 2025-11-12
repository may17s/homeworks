package com.may.a03.repository;

import com.may.a03.model.MedicalNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicalNoteRepository extends JpaRepository<MedicalNote, Long> {
    List<MedicalNote> findByDeletedFalse();
    List<MedicalNote> findByPatientIdAndDeletedFalse(Long patientId);
}