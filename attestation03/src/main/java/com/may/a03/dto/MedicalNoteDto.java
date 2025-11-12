package com.may.a03.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicalNoteDto {
    private Long id;
    private Long patientId;
    private Long diseaseId;
    private LocalDate noteDate;
    private String note;
}
