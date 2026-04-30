package com.sylektus.dto;

public record AppointmentRequest(

        String fullName,
        String mobile,
        String email,
        String linkedin,
        String companyName,
        String message

) {}
