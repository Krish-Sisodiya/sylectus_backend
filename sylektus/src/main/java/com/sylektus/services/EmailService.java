package com.sylektus.services;

import com.sylektus.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendAppointmentNotification(Appointment appointment) {
        SimpleMailMessage message = new SimpleMailMessage();

        // Jise email bhejna hai (Founder Email)
        message.setTo("connect.sylektus@gmail.com");

        // Subject line thodi catchy rakhte hain
        message.setSubject("New Project Inquiry from: " + appointment.getFullName());

        // Saara data body mein format kar diya hai
        String body = "Bhai, Sylekt Us ki website par ek naya inquiry/appointment aaya hai:\n\n" +
                "--------------------------------------\n" +
                "👤 Full Name: " + appointment.getFullName() + "\n" +
                "📞 Mobile: " + appointment.getMobile() + "\n" +
                "📧 Email: " + appointment.getEmail() + "\n" +
                "🏢 Company: " + (appointment.getCompanyName() != null ? appointment.getCompanyName() : "N/A") + "\n" +
                "🔗 LinkedIn: " + (appointment.getLinkedin() != null ? appointment.getLinkedin() : "N/A") + "\n" +
                "--------------------------------------\n" +
                "📝 Message:\n" + appointment.getMessage() + "\n" +
                "--------------------------------------\n\n" +
                "Check kar lo aur contact kar lo jaldi!";

        message.setText(body);

        // Aapka sender email (application.properties wala)
        message.setFrom("krishsisodiya7389@gmail.com");

        try {
            mailSender.send(message);
            System.out.println("Inquiry Email Sent Successfully to Founder!");
        } catch (Exception e) {
            System.err.println("Error while sending email: " + e.getMessage());
        }
    }
}