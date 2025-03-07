package com.bharath.patientclinicals.clinicalsapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bharath.patientclinicals.clinicalsapi.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}