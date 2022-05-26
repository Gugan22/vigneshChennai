package com.vignesh.details.jpaEntity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "")
@Data
@Builder
public class StudentDetails {
	@Column(name = "std_id")
	@Id
	@JsonProperty("id")
	private BigInteger id;
	
	@Column(name = "std_name")
	@JsonProperty("name")
	private String name;
	
	@Column(name = "std_email")
	@JsonProperty("email")
	private String email;

	@Column(name = "std_address")
	@JsonProperty("address")
	private String address;

	@Column(name = "std_phoneno")
	@JsonProperty("phoneno")
	private String phoneNo;

	@Column(name = "std_grade")
	@JsonProperty("grade")
	private String grade;

	@Column(name = "std_section")
	@JsonProperty("section")
	private String section;

}
