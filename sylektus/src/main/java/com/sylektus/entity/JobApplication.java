package com.sylektus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String mobile;
    private String email;
    private String education;
    private String linkedin;
    private String lookingFor;

    @Column(length=1000)
    private String coverLetter;

    private String resumeFileName;

}
