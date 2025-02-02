package com.bharath.patientclinicals.clinicalsapi.controllers;

import com.bharath.patientclinicals.clinicalsapi.entities.ClinicalData;
import com.bharath.patientclinicals.clinicalsapi.repositories.ClinicalDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClinicalDataController.class)
class ClinicalDataControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ClinicalDataRepository clinicalDataRepo;

    @Autowired
    ObjectMapper objectMapper;

    ClinicalData clinicalData;

    @BeforeEach
    void setUp() {
        clinicalData = new ClinicalData();
        clinicalData.setId(1L);
        clinicalData.setComponentName("Blood Pressure");
        clinicalData.setComponentValue("120/80");
    }

    @Test
    void getClinicalDataById_returnsClinicalData_whenIdExists() throws Exception {
        given(clinicalDataRepo.findById(1L)).willReturn(Optional.of(clinicalData));

        mockMvc.perform(get("/api/clinicaldata/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.componentName").value("Blood Pressure"))
                .andExpect(jsonPath("$.componentValue").value("120/80"))
                .andDo(print());
    }

    @Test
    void getClinicalDataById_returnsNotFound_whenIdDoesNotExist() throws Exception {
        given(clinicalDataRepo.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/clinicaldata/1"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void createClinicalData_createsAndReturnsClinicalData() throws Exception {
        given(clinicalDataRepo.save(Mockito.any(ClinicalData.class))).willReturn(clinicalData);

        mockMvc.perform(post("/api/clinicaldata")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clinicalData)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.componentName").value("Blood Pressure"))
                .andExpect(jsonPath("$.componentValue").value("120/80"))
                .andDo(print());
    }

    @Test
    void updateClinicalData_updatesAndReturnsClinicalData_whenIdExists() throws Exception {
        ClinicalData updatedClinicalData = new ClinicalData();
        updatedClinicalData.setId(1L);
        updatedClinicalData.setComponentName("Heart Rate");
        updatedClinicalData.setComponentValue("75");

        given(clinicalDataRepo.findById(1L)).willReturn(Optional.of(clinicalData));
        given(clinicalDataRepo.save(Mockito.any(ClinicalData.class))).willReturn(updatedClinicalData);

        mockMvc.perform(put("/api/clinicaldata/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedClinicalData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.componentName").value("Heart Rate"))
                .andExpect(jsonPath("$.componentValue").value("75"))
                .andDo(print());
    }

    @Test
    void updateClinicalData_returnsNotFound_whenIdDoesNotExist() throws Exception {
        ClinicalData updatedClinicalData = new ClinicalData();
        updatedClinicalData.setId(1L);
        updatedClinicalData.setComponentName("Heart Rate");
        updatedClinicalData.setComponentValue("75");

        given(clinicalDataRepo.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(put("/api/clinicaldata/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedClinicalData)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void deleteClinicalData_deletesClinicalData_whenIdExists() throws Exception {
        given(clinicalDataRepo.findById(1L)).willReturn(Optional.of(clinicalData));

        mockMvc.perform(delete("/api/clinicaldata/1"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void deleteClinicalData_returnsNotFound_whenIdDoesNotExist() throws Exception {
        given(clinicalDataRepo.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(delete("/api/clinicaldata/1"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}