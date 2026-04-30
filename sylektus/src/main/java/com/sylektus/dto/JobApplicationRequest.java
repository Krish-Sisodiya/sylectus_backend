package com.sylektus.dto;

public record JobApplicationRequest(

        String fullName,
        String mobile,
        String email,
        String education,
        String linkedin,
        String lookingFor,
        String coverLetter

){}
