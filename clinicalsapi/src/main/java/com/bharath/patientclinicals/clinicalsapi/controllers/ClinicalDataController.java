package com.bharath.patientclinicals.clinicalsapi.controllers;

import com.bharath.patientclinicals.clinicalsapi.entities.ClinicalData;
import com.bharath.patientclinicals.clinicalsapi.repositories.ClinicalDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/clinicaldata")
public class ClinicalDataController {

    private final ClinicalDataRepository clinicalDataRepository;

    @GetMapping
    public List<ClinicalData> getAllClinicalData() {
        return clinicalDataRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalData> getClinicalDataById(@PathVariable Long id) {
        Optional<ClinicalData> clinicalData = clinicalDataRepository.findById(id);
        return clinicalData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClinicalData> createClinicalData(@RequestBody ClinicalData clinicalData) {
        ClinicalData savedClinicalData = clinicalDataRepository.save(clinicalData);
        return new ResponseEntity<>(savedClinicalData, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicalData> updateClinicalData(@PathVariable Long id, @RequestBody ClinicalData clinicalDataDetails) {
        return clinicalDataRepository.findById(id)
                .map(clinicalData -> {
                    clinicalData.setComponentName(clinicalDataDetails.getComponentName());
                    clinicalData.setComponentValue(clinicalDataDetails.getComponentValue());
                    clinicalData.setMeasuredDateTime(clinicalDataDetails.getMeasuredDateTime());
                    clinicalData.setPatient(clinicalDataDetails.getPatient());
                    return ResponseEntity.ok(clinicalDataRepository.save(clinicalData));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinicalData(@PathVariable Long id) {
        Optional<ClinicalData> clinicalData = clinicalDataRepository.findById(id);
        if (clinicalData.isPresent()) {
            clinicalDataRepository.delete(clinicalData.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}