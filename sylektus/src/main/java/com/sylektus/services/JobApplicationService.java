package com.sylektus.services;

import com.sylektus.entity.JobApplication;
import com.sylektus.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository repo;
    private final JobEmailService emailService;

    public void submitApplication(

            String fullName,
            String mobile,
            String email,
            String education,
            String linkedin,
            String lookingFor,
            String coverLetter,
            MultipartFile resume

    ) throws Exception {

        JobApplication app = new JobApplication();

        app.setFullName(fullName);
        app.setMobile(mobile);
        app.setEmail(email);
        app.setEducation(education);
        app.setLinkedin(linkedin);
        app.setLookingFor(lookingFor);
        app.setCoverLetter(coverLetter);

        if (resume != null) {
            app.setResumeFileName(resume.getOriginalFilename());
        }

        repo.save(app);

        // 📩 EMAIL 1 → USER
        emailService.sendCandidateConfirmation(email, fullName);

        // 📩 EMAIL 2 → COMPANY (with resume)
        emailService.sendAdminApplicationMail(
                fullName,
                mobile,
                email,
                education,
                linkedin,
                lookingFor,
                coverLetter,
                resume
        );
    }

}