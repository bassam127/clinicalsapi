package com.clinicals.api.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.clinicals.api.util.JpaUtil;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "first_name")
	private String firstName;


	@Column(name = "age")
	private Integer age;

	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER , mappedBy = "patient")
	private List<ClinicalData> clinicalDatas;


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public List<ClinicalData> getClinicalDatas() {
		clinicalDatas = JpaUtil.getNullOrValue(clinicalDatas);
		return clinicalDatas;
	}

}
