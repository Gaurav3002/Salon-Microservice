package com.adlock.service;

import com.adlock.domain.BookingStatus;
import com.adlock.dto.BookingRequest;
import com.adlock.dto.SalonDto;
import com.adlock.dto.ServiceOfferingDto;
import com.adlock.dto.UserDTO;
import com.adlock.model.Booking;
import com.adlock.model.SalonRepot;

import java.time.LocalDate;
import java.util.*;

public interface BookingService {

    Booking createBooking(BookingRequest booking, UserDTO userDTO, SalonDto salonDto, Set<ServiceOfferingDto> servicedto) throws Exception;
    List<Booking> getBokingsByCustomer(Long customerId);
    List<Booking> getBookingsBySalon(Long salonId);
    Booking getBookingById(Long id) throws Exception;
    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;
    List<Booking> getBookingByDates(LocalDate date, Long salonId);
    SalonRepot getSalonReport(long salonId);



}
