package com.sylektus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="appointments")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String fullName;

    @Column(nullable=false)
    private String mobile;

    @Column(nullable=false)
    private String email;

    private String linkedin;

    private String companyName;

    @Column(length=500)
    private String message;

    private String createdAt;

}