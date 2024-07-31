package com.example.ensetdemospringang.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Setter @Getter @ToString @Builder
public class Student {
    @Id
    private String id;
    private String firstName ;
    private String lastName;
    private String email;
    @Column(unique = true)
    private String code;
    private String programId;
    private String photo;


}
