package com.sylektus.repository;

import com.sylektus.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication,Long> {

}
