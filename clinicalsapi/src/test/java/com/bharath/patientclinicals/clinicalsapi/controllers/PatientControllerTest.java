package com.bharath.patientclinicals.clinicalsapi.controllers;

import com.bharath.patientclinicals.clinicalsapi.entities.Patient;
import com.bharath.patientclinicals.clinicalsapi.repositories.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    PatientRepository patientRepository;

    @Autowired
    ObjectMapper objectMapper;

    Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setAge(30);
    }

    @Test
    void getAllPatients_returnsAllPatients() throws Exception {
        given(patientRepository.findAll()).willReturn(List.of(patient));

        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].age").value(30));
    }

    @Test
    void getPatientById_returnsPatient_whenIdExists() throws Exception {
        given(patientRepository.findById(1L)).willReturn(Optional.of(patient));

        mockMvc.perform(get("/api/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.age").value(30));
    }

    @Test
    void getPatientById_returnsNotFound_whenIdDoesNotExist() throws Exception {
        given(patientRepository.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/patients/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createPatient_createsAndReturnsPatient() throws Exception {
        given(patientRepository.save(Mockito.any(Patient.class))).willReturn(patient);

        mockMvc.perform(post("/api/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.age").value(30));
    }

    @Test
    void updatePatient_updatesAndReturnsPatient_whenIdExists() throws Exception {
        Patient updatedPatient = new Patient();
        updatedPatient.setId(1L);
        updatedPatient.setFirstName("Jane");
        updatedPatient.setLastName("Doe");
        updatedPatient.setAge(28);

        given(patientRepository.findById(1L)).willReturn(Optional.of(patient));
        given(patientRepository.save(Mockito.any(Patient.class))).willReturn(updatedPatient);

        mockMvc.perform(put("/api/patients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPatient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.age").value(28));
    }

    @Test
    void updatePatient_returnsNotFound_whenIdDoesNotExist() throws Exception {
        Patient updatedPatient = new Patient();
        updatedPatient.setId(1L);
        updatedPatient.setFirstName("Jane");
        updatedPatient.setLastName("Doe");
        updatedPatient.setAge(28);

        given(patientRepository.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(put("/api/patients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPatient)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePatient_deletesPatient_whenIdExists() throws Exception {
        given(patientRepository.findById(1L)).willReturn(Optional.of(patient));

        mockMvc.perform(delete("/api/patients/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deletePatient_returnsNotFound_whenIdDoesNotExist() throws Exception {
        given(patientRepository.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(delete("/api/patients/1"))
                .andExpect(status().isNotFound());
    }
}