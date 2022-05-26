package com.vignesh.marks.jpaRepository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vignesh.marks.jpaEntity.StudentMarks;

@Repository
public interface StudentMarksRepository extends CrudRepository<StudentMarks, BigInteger>{
		
}
