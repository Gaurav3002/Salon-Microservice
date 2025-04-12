package com.adlock.model;

import com.adlock.domain.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long salonId;
    private Long customerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    // by using eliment collection spring boot create seperate table of the servces ids
    @ElementCollection
    private Set<Long> serviceIds;
    private BookingStatus status =  BookingStatus.PENDING;
    private Long totalPrices;

}
