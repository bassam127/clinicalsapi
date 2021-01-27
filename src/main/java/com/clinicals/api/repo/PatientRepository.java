package com.clinicals.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicals.api.model.Patient;


public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
