package com.may.a03.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MedicalNoteNotFoundException extends RuntimeException {
    public MedicalNoteNotFoundException (Long id) {
        super("Медицинская запись с ID = " + id + " не найдена!");
    }
}