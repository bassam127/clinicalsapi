package com.clinicals.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicals.api.model.ClinicalData;

public interface ClinicalsDataRepository extends JpaRepository<ClinicalData, Integer> {

}
