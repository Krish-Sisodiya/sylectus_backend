package com.sylektus.repository;

import com.sylektus.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository
        extends JpaRepository< Appointment,Long>{

}
