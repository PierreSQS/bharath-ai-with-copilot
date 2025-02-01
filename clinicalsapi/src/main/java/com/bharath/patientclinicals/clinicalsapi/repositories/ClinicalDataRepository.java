package com.bharath.patientclinicals.clinicalsapi.repositories;

import com.bharath.patientclinicals.clinicalsapi.entities.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {
}