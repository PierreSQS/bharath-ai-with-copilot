package com.bharath.patientclinicals.clinicalsapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.patientclinicals.clinicalsapi.dto.ClinicalDataRequest;
import com.bharath.patientclinicals.clinicalsapi.models.ClinicalData;
import com.bharath.patientclinicals.clinicalsapi.models.Patient;
import com.bharath.patientclinicals.clinicalsapi.repos.ClinicalDataRepository;
import com.bharath.patientclinicals.clinicalsapi.repos.PatientRepository;


@RestController
@RequestMapping("/clinicaldata")
public class ClinicalDataController {

    @Autowired
    private ClinicalDataRepository clinicalDataRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public List<ClinicalData> getAllClinicalData() {
        return clinicalDataRepository.findAll();
    }

    @GetMapping("/{id}")
    public ClinicalData getClinicalData(@PathVariable Long id) {
        return clinicalDataRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ClinicalData createClinicalData(@RequestBody ClinicalData clinicalData) {
        return clinicalDataRepository.save(clinicalData);
    }

    @PutMapping("/{id}")
    public ClinicalData updateClinicalData(@PathVariable Long id, @RequestBody ClinicalData clinicalData) {
        clinicalData.setId(id);
        return clinicalDataRepository.save(clinicalData);
    }

    @DeleteMapping("/{id}")
    public void deleteClinicalData(@PathVariable Long id) {
        clinicalDataRepository.deleteById(id);
    }

    //method that receives patient id, clinical data and saves it to the database
    @PostMapping("/clinicals")
    public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request) {
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setComponentName(request.getComponentName());
        clinicalData.setComponentValue(request.getComponentValue());

        Patient patient = patientRepository.findById(request.getPatientId()).get();

        clinicalData.setPatient(patient);
        return clinicalDataRepository.save(clinicalData);
    }

}