package com.bharath.patientclinicals.clinicalsapi;

import com.bharath.patientclinicals.clinicalsapi.controllers.PatientController;
import com.bharath.patientclinicals.clinicalsapi.models.Patient;
import com.bharath.patientclinicals.clinicalsapi.repos.PatientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    public void testGetAllPatients() throws Exception {
        when(patientRepository.findAll()).thenReturn(Arrays.asList(new Patient(), new Patient()));
        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void testGetPatient() throws Exception {
        Patient patient = new Patient();
        patient.setId(1L);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk());
        verify(patientRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreatePatient() throws Exception {
        Patient patient = new Patient();
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);
        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    public void testUpdatePatient() throws Exception {
        Patient patient = new Patient();
        patient.setId(1L);
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);
        mockMvc.perform(put("/patients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    public void testDeletePatient() throws Exception {
        doNothing().when(patientRepository).deleteById(1L);
        mockMvc.perform(delete("/patients/1"))
                .andExpect(status().isOk());
        verify(patientRepository, times(1)).deleteById(1L);
    }
}