package com.may.a03.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Data
public class PatientDto {
    private Long id;
    private String fullName;
    private java.time.LocalDate birthDate;
    private String gender;
    private String phone;
    private String address;
}