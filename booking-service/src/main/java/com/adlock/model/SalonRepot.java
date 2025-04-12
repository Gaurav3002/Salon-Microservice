package com.adlock.model;

import lombok.Data;

@Data
public class SalonRepot {
    private Long salonId;
    private String name;
    private int totalEarnings;
    private Integer totalBookings;
    private Integer cancelledBookings;
    private Double totalRefund;
}
