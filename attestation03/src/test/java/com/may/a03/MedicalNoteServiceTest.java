package com.may.a03;

import com.may.a03.dto.MedicalNoteDto;
import com.may.a03.model.MedicalNote;
import com.may.a03.repository.MedicalNoteRepository;
import com.may.a03.service.MedicalNoteService;
import com.may.a03.service.PatientService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = A03Application.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MedicalNoteServiceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MedicalNoteService service;

    @Autowired
    private MedicalNoteRepository repository;

    @Autowired
    private PatientService patientService;


    @Test
    @Order(1)
    void shouldCreateMedicalNote() {
        MedicalNoteDto dto = new MedicalNoteDto();
        dto.setPatientId(1L);
        dto.setDiseaseId(1L);
        dto.setNoteDate(LocalDate.now());
        dto.setNote("Артериальная гипертензия");

        MedicalNoteDto saved = service.create(dto);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    @Order(2)
    void shouldSoftDeleteNote() {
        MedicalNoteDto dto = new MedicalNoteDto();
        dto.setPatientId(1L);
        dto.setDiseaseId(1L);
        dto.setNoteDate(LocalDate.now());
        dto.setNote("Запись для проверки soft delete");
        MedicalNoteDto saved = service.create(dto);

        // Удаляем её
        service.softDelete(saved.getId());

        MedicalNote record = repository.findById(saved.getId()).orElse(null);
        assertThat(record).isNotNull();
        assertThat(record.isDeleted()).isTrue();
    }
    @Test
    @Order(3)
    void shouldUpdateMedicalNote() {
        // Создание
        MedicalNoteDto dto = new MedicalNoteDto();
        dto.setPatientId(1L);
        dto.setDiseaseId(1L);
        dto.setNoteDate(LocalDate.now());
        dto.setNote("Изначальная запись");

        MedicalNoteDto saved = service.create(dto);

        // Обновление
        MedicalNoteDto updateDto = new MedicalNoteDto();
        updateDto.setPatientId(1L);
        updateDto.setDiseaseId(2L);
        updateDto.setNoteDate(LocalDate.now().plusDays(1));
        updateDto.setNote("Запись обновлена");

        MedicalNoteDto updated = service.update(saved.getId(), updateDto);

        assertThat(updated.getId()).isEqualTo(saved.getId());
        assertThat(updated.getDiseaseId()).isEqualTo(2L);
        assertThat(updated.getNote()).isEqualTo("Запись обновлена");
    }

    @Test
    @Order(4)
    void shouldGetAllActiveNotes() {
        MedicalNoteDto dto = new MedicalNoteDto();
        dto.setPatientId(2L);
        dto.setDiseaseId(3L);
        dto.setNoteDate(LocalDate.now());
        dto.setNote("Активная запись для теста GET");
        service.create(dto);

        var list = service.getAllActive();
        assertThat(list).isNotEmpty();
        assertThat(list).anyMatch(n -> n.getNote().contains("Активная запись"));
    }

    @Test
    @Order(5)
    void shouldFindById() {
        MedicalNoteDto dto = new MedicalNoteDto();
        dto.setPatientId(1L);
        dto.setDiseaseId(1L);
        dto.setNoteDate(LocalDate.now());
        dto.setNote("Запись для теста findById");
        MedicalNoteDto saved = service.create(dto);

        MedicalNoteDto found = service.findById(saved.getId());

        assertThat(found.getId()).isEqualTo(saved.getId());
        assertThat(found.getNote()).isEqualTo("Запись для теста findById");
    }

    @Test
    @Order(6)
    public void givenPatients_whenGetPatients()
            throws Exception {

        mvc.perform(get("/api/patients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].fullName", is("Петров Иван Иваныч")));
    }

    @Test
    @Order(7)
    public void givenPatients_whenGetAll_thenReturnsNoEmpty() throws Exception {
        mvc.perform(get("/api/patients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", not(empty())));
    }
}