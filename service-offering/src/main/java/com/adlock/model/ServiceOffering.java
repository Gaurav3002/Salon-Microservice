package com.adlock.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ServiceOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private double duration;
    @Column(nullable = false)
    private Long salonId;
    @Column(nullable = false)
    private Long categoryId;

    private String image;
}
