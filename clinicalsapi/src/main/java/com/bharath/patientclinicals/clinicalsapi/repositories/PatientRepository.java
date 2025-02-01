package com.bharath.patientclinicals.clinicalsapi.repositories;

import com.bharath.patientclinicals.clinicalsapi.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
