package com.sylektus.services;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class JobEmailService {

    private final JavaMailSender mailSender;

    // 📩 COMPANY MAIL (Admin)
    public void sendAdminApplicationMail(

            String name,
            String mobile,
            String email,
            String education,
            String linkedin,
            String role,
            String coverLetter,
            MultipartFile resume

    ) throws Exception {

        MimeMessage msg = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(msg, true);

        helper.setTo("krishsisodiya7389@gmail.com");

        helper.setSubject("🚀 New Employee Application Received");

        // 🔥 IMPORTANT (Reply goes to user)
        helper.setReplyTo(email);

        String html = """
<h2>🚀 New Candidate Application</h2>

<p><b>Name:</b> %s</p>
<p><b>Mobile:</b> %s</p>
<p><b>Email:</b> %s</p>
<p><b>Education:</b> %s</p>
<p><b>LinkedIn:</b> %s</p>
<p><b>Position:</b> %s</p>

<hr/>

<p><b>Cover Letter:</b><br>%s</p>

"""
                .formatted(
                        name,
                        mobile,
                        email,
                        education,
                        linkedin,
                        role,
                        coverLetter
                );

        helper.setText(html, true);

        // 📎 Resume attach
        if (resume != null && !resume.isEmpty()) {
            helper.addAttachment(
                    resume.getOriginalFilename(),
                    resume
            );
        }

        mailSender.send(msg);
    }


    // 📩 USER CONFIRMATION MAIL
    public void sendCandidateConfirmation(
            String email,
            String name
    ) throws Exception {

        MimeMessage msg = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(msg, true);

        helper.setTo(email);

        helper.setSubject("Application Received - Sylect Us");

        String html = """
<h2>Hello %s,</h2>

<p>
Thank you for applying at <b>Sylect Us</b>.
We have successfully received your application.
</p>

<p>
Our hiring team will review your profile and respond within <b>48 hours</b>.
</p>

<p>
If you have any questions, feel free to reply to this email.
</p>

<br/>

<p>
Regards,<br>
<b>Sylect Us Hiring Team</b>
</p>
"""
                .formatted(name);

        helper.setText(html, true);

        mailSender.send(msg);
    }

}