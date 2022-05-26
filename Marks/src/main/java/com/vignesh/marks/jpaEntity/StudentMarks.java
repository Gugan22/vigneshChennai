package com.vignesh.marks.jpaEntity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name="")
@Data
@Getter
@Builder
public class StudentMarks {
	
	@Column(name = "std_id")
	@Id
	@JsonProperty("id")
	public BigInteger id;
	
	@Column(name = "session")
	@JsonProperty("session")
	public String session;
	
	@Column(name = "semester")
	@JsonProperty("semester")
	public String semester;

	@Column(name = "total_subjects")
	@JsonProperty("totalSubjects")
	public int totalSubjects;

	@Column(name = "total_marks")
	@JsonProperty("totalMarks")
	public String totalMarks;

}
