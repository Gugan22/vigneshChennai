package com.vignesh.marks.api;

import java.math.BigInteger;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.common.net.MediaType;
import com.vignesh.marks.jpaEntity.StudentMarks;
import com.vignesh.marks.jpaRepository.StudentMarksRepository;

import antlr.collections.List;

@RestController
@RequestMapping("/student")
public class Controller {
	
	@Autowired
	WebClient.Builder webClient;
	
	@Autowired
	StudentMarksRepository markRepo;
	
	@GetMapping("/test")
	public String test() {
		return "Good";
	}
	
	
	@PostMapping(path = "/marks/details")
	public ResponseEntity<String> saveDetails(@RequestBody StudentMarks mark){
		if(mark.id != null && webClient.build().get().uri("http://Details/student/isIdPresent/{id}", mark.id).retrieve().bodyToMono(Boolean.class).block().booleanValue()) {
				markRepo.save(mark);
				return ResponseEntity.ok(new JSONObject().put("message", "Record inserted successfully").put("code", 00).toString());
			}
			else
				return ResponseEntity.ok(new JSONObject().put("message", "Record insertion failed").put("code", 99).toString());

	}
	
	@GetMapping("/marks/{id}")
	public ResponseEntity<StudentMarks> getMarks(@PathVariable String id){
		try {
			return ResponseEntity.ok(markRepo.findById(new BigInteger(id)).get());
		} catch (Exception e) {
			return  (ResponseEntity<StudentMarks>) ResponseEntity.badRequest();
		}
	}

}
