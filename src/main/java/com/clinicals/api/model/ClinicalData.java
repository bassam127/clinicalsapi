package com.clinicals.api.model;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.clinicals.api.util.JpaUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clinicaldata")
@JsonIgnoreProperties({"patient"})
public class ClinicalData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "component_name")
	private String componentName;

	@Column(name = "component_value")
	private String componentValue;

	@Column(name = "measured_date_time")
	private Timestamp measuredDataTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id" ,nullable = false)
	private Patient patient;

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentValue() {
		return componentValue;
	}

	public void setComponentValue(String componentValue) {
		this.componentValue = componentValue;
	}

	public Timestamp getMeasuredDataTime() {
		return measuredDataTime;
	}

	public void setMeasuredDataTime(Timestamp measuredDataTime) {
		this.measuredDataTime = measuredDataTime;
	}

	public Integer getId() {
		return id;
	}

	public Patient getPatient() {
		patient = JpaUtil.getNullOrValue(patient);
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	

}
