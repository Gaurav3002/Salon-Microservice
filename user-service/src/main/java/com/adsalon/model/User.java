package com.adsalon.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "userName is mandatory")
    private String userName;
    private String fullName;

    @NotBlank(message = "password is mandatory")
    @Email(message = "Email should be valid")
    private  String email;

    private String phone;

    @NotBlank(message = "Role is mandatory")
    private String role;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NotBlank(message = "password is mandatory")
    private String password;




    public User(String fullName, String email, String phone, String role, LocalDateTime createdAt, LocalDateTime updatedAt, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.password = password;
    }








}
