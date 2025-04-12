package com.adlock.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ServiceOfferingDto {

    private Long id;

    private String name;

    private String description;

    private double price;

    private double duration;

    private Long salonId;

    private Long categoryId;

    private String image;
}
