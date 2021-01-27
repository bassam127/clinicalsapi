package com.clinicals.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinicals.api.dto.ClinicalDataRequest;
import com.clinicals.api.model.ClinicalData;
import com.clinicals.api.model.Patient;
import com.clinicals.api.repo.ClinicalsDataRepository;
import com.clinicals.api.repo.PatientRepository;

@RestController
@CrossOrigin( origins = "*" )
public class ClinicalDatacontroller {

	@Autowired
	ClinicalsDataRepository clinicalsDataRepository;

	@Autowired
	PatientRepository patientRepository;

	@RequestMapping(value = "/createclinicaldata" , method = RequestMethod.POST , consumes = "application/json", produces = "application/json")
	public ClinicalData createClinicalsData(@RequestBody ClinicalDataRequest clinicalDataRequest) {
 		Patient patient = patientRepository.findById(clinicalDataRequest.getPatientId()).get();
		ClinicalData clinicalData = new ClinicalData();
		clinicalData.setPatient(patient);
		clinicalData.setComponentName(clinicalDataRequest.getComponentName());
		clinicalData.setComponentValue(clinicalDataRequest.getComponentValue());

		return clinicalsDataRepository.save(clinicalData);
	}

	@RequestMapping(value = "/getclinicaldata", method = RequestMethod.GET)
	public ClinicalData getClinicaldataById(@RequestParam(value = "id")  Integer id) {
		return clinicalsDataRepository.findById(id).get();
	}

}
