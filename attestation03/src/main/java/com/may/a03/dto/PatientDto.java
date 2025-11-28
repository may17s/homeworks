package com.may.a03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность пациент")
public class PatientDto {
    @Schema(description = "Уникальный идентификатор пациента", example = "2", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "ФИО", example = "Лебедев Алексей Юрьевич")
    private String fullName;
    @Schema(description = "Дата рождения", example = "1995-12-03")
    private java.time.LocalDate birthDate;
    @Schema(description = "Пол", allowableValues = {"М", "Ж"})
    private String gender;
    @Schema(description = "Номер телефона", example = "+79344445566")
    private String phone;
    @Schema(description = "Адрес", example = "г. Самара, ул. Гагарина, д.22")
    private String address;
}