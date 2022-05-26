package com.vignesh.details.jpaRepository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vignesh.details.jpaEntity.StudentDetails;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails, BigInteger>{

}
