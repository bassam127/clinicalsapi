package com.clinicals.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.clinicals.api.model.ClinicalData;
import com.clinicals.api.model.Patient;
import com.clinicals.api.repo.PatientRepository;

@RestController
@CrossOrigin( origins = "*" )
public class PatientController {

	@Autowired
	PatientRepository patientRepository;

	@RequestMapping(value = "/createpatient", method = RequestMethod.POST)
	public Patient createPatient(@RequestBody Patient patient) {
		return patientRepository.save(patient);
	}

	@RequestMapping(value = "/getpatient", method = RequestMethod.GET)
	public Patient getPatientById( @RequestParam(value = "id")  Integer id) {
		return patientRepository.findById(id).get();
	}

	@RequestMapping(value = "/getallpatient", method = RequestMethod.GET)
	@ResponseBody
	public List<Patient> getPatients() {
		return patientRepository.findAll();
	}
	
	
	@RequestMapping(value = "/patient/analyze", method = RequestMethod.GET)
	public Patient analyze(@RequestParam(value = "id") Integer id) {
		Patient patient = patientRepository.findById(id).get();
		List<ClinicalData> clinicalDatas = patient.getClinicalDatas();
		for (ClinicalData clinicalData : clinicalDatas) {
			if (clinicalData.getComponentName().equals("hw")) {
				String[] heightAndWeight = clinicalData.getComponentValue().split("/");
				String heigt = heightAndWeight[0];
				String weight = heightAndWeight[1];
				float heighInmeter = Float.parseFloat(heigt) * 0.4536F;
				Float bmi = Float.parseFloat(weight) / (heighInmeter * heighInmeter);
				ClinicalData bmiData = new ClinicalData();
				bmiData.setComponentName("bmi");
				bmiData.setComponentValue(bmi.toString());
				patient.getClinicalDatas().add(bmiData);
			}

		}
		return patient;
	}

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String testingApi() {
		return "hi ping";
	}

}
