package com.vignesh.details.web;

import static org.hamcrest.CoreMatchers.nullValue;

import java.math.BigInteger;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.inject.PrivateBinder;
import com.vignesh.details.jpaEntity.StudentDetails;
import com.vignesh.details.jpaRepository.StudentDetailsRepository;


@RestController
@RequestMapping("/student")
public class Controller {
	
	@Autowired
	StudentDetailsRepository detailsRepo;

	@Autowired	
	private WebClient.Builder webClient;
	
	@GetMapping("/test")
	public ResponseEntity<String> test(){
		 System.out.println("kiiiii");
		System.out.println(webClient.build().get().uri("http://Marks/student/test").retrieve().toEntity(String.class));
		return ResponseEntity.ok("");
	}
	
	@PostMapping("/details")
	public ResponseEntity<String> saveDetails(@RequestBody List<StudentDetails> details){
		try {
			detailsRepo.saveAll(details);
			return  ResponseEntity.ok(new JSONObject().put("message",  details.size()+" inserted successfully").put("code", 00).toString());

		} catch (Exception e) {
			return  ResponseEntity.ok(new JSONObject().put("message",  "Data Failed to save").put("code", 99).toString());
		}
	}
	
	@GetMapping("/isIdPresent/{id}")
	public ResponseEntity<Boolean> isStudentPresent(@PathVariable String id){
		if(detailsRepo.findById(new BigInteger(id))!=null) return ResponseEntity.ok(true);
		else  return ResponseEntity.ok(false);
	}
	
	@PostMapping("/student/details/{id}")
	public ResponseEntity<String> getDetails(@PathVariable BigInteger id){
		try {
			if(detailsRepo.findById(id)!=null)
				return ResponseEntity.ok(new JSONObject().put("studentInfo", detailsRepo.findById(id))
						.put("studentMarks", webClient.build().get().uri("http://Marks/marks/{id}", id).accept(MediaType.APPLICATION_JSON)
								.retrieve().bodyToMono(JSONObject.class).block()).toString());
			else return ResponseEntity.ok(new JSONObject().put("message", "No data found").put("code", 99).toString());
		}catch (Exception e) {
			return ResponseEntity.ok(new JSONObject().put("message", "Internal server error").put("code", 99).toString());
		}
		
	}
	
	
	@GetMapping("/details")
	public ResponseEntity<List<StudentDetails>> getAll(){
		return ResponseEntity.ok(detailsRepo.findAll());
	}
	
}
