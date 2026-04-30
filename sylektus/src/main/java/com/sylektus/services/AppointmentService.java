package com.sylektus.services;

import java.time.LocalDateTime;
import com.sylektus.dto.AppointmentRequest;
import com.sylektus.entity.Appointment;
import com.sylektus.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;
    private final EmailService emailService; // 1. EmailService ko add kiya

    // Constructor mein dono dependencies inject karein
    public AppointmentService(
            AppointmentRepository repository,
            EmailService emailService
    ){
        this.repository = repository;
        this.emailService = emailService;
    }

    public void saveAppointment(AppointmentRequest request) {

        Appointment appointment = new Appointment();

        appointment.setFullName(request.fullName());
        appointment.setMobile(request.mobile());
        appointment.setEmail(request.email());
        appointment.setLinkedin(request.linkedin());
        appointment.setCompanyName(request.companyName());
        appointment.setMessage(request.message());

        // Proper ISO format ya simple string
        appointment.setCreatedAt(LocalDateTime.now().toString());

        // 2. Pehle database mein save karein
        Appointment savedAppointment = repository.save(appointment);

        // 3. Save hone ke baad Email bhejein
        try {
            emailService.sendAppointmentNotification(savedAppointment);
        } catch (Exception e) {
            // Agar email fail ho jaye toh error console par dikhe,
            // par user ka form submission na ruke (isliye try-catch)
            System.err.println("Database saved but Email failed: " + e.getMessage());
        }
    }
}