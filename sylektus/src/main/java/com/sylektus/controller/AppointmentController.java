package com.sylektus.controller;

import com.sylektus.dto.AppointmentRequest;
import com.sylektus.services.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*") // Global access for testing, production mein ise restrict kar sakte hain
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> submitAppointment(@RequestBody AppointmentRequest request) {
        try {
            // Service ab data save bhi karegi aur email bhi bhejegi
            service.saveAppointment(request);

            return ResponseEntity.ok("Bhai, appointment submit ho gaya hai aur founder ko email bhej diya gaya hai!");

        } catch (Exception e) {
            // Agar kuch gadbad hoti hai toh user ko error message mile
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Oops! Kuch dikkat aa gayi: " + e.getMessage());
        }
    }
}