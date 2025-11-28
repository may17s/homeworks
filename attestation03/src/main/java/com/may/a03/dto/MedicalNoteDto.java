package com.may.a03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;

@Data
@Schema(description = "Сущность медицинская запись")
public class MedicalNoteDto {
    @Schema(description = "Уникальный идентификатор медицинской записи", example = "3", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Ссылка на пациента", example = "6")
    private Long patientId;
    @Schema(description = "Ссылка на заболевания", example = "5")
    private Long diseaseId;
    @Schema(description = "Дата медицинской записи", example = "2025-10-10")
    private LocalDate noteDate;
    @Schema(description = "Содержимое медицинской записи", example = "Прохождение диспансеризации. Все показатели в пределах нормы.")
    private String note;
}
