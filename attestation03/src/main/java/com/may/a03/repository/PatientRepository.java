package com.may.a03.repository;

import com.may.a03.model.MedicalNote;
import com.may.a03.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByFullNameContainingIgnoreCase(String name);
    List<Patient> findPatientsByGender(String gender);
}