package com.may.a03.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "patients")
@Comment("Пациент")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private java.time.LocalDate birthDate;
    private String gender;
    private String phone;
    private String address;
}