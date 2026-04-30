package com.sylektus.controller;

import com.sylektus.services.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/careers")
@CrossOrigin("*")
@RequiredArgsConstructor

public class CareerController {

    private final JobApplicationService service;


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> apply(

            @RequestParam String fullName,
            @RequestParam String mobile,
            @RequestParam String email,
            @RequestParam String education,
            @RequestParam String linkedin,
            @RequestParam String lookingFor,
            @RequestParam String coverLetter,

            @RequestParam(required = false)
            MultipartFile resume

    ) throws Exception {

        service.submitApplication(
                fullName,
                mobile,
                email,
                education,
                linkedin,
                lookingFor,
                coverLetter,
                resume
        );

        return ResponseEntity.ok("Application Submitted");
    }

}