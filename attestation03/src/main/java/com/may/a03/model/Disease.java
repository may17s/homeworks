package com.may.a03.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "diseases")
@Comment("Болезнь")
@Data
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
}