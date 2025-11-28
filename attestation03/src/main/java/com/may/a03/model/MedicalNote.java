package com.may.a03.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "medical_notes")
@Comment("Запись")
@Data
public class MedicalNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "disease_id", nullable = false)
    private Disease disease;

    private java.time.LocalDate noteDate;
    private String note;

    @Column(name = "is_deleted")
    private boolean deleted = false;
}
