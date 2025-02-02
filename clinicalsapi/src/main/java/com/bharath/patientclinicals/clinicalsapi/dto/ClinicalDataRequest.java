package com.bharath.patientclinicals.clinicalsapi.dto;

import com.bharath.patientclinicals.clinicalsapi.entities.Patient;
import lombok.Data;

@Data
public class ClinicalDataRequest {
    private String componentName;
    private String componentValue;
    private Patient patient;
}
