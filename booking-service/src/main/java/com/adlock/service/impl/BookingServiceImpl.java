package com.adlock.service.impl;

import com.adlock.domain.BookingStatus;
import com.adlock.dto.BookingRequest;
import com.adlock.dto.SalonDto;
import com.adlock.dto.ServiceOfferingDto;
import com.adlock.dto.UserDTO;
import com.adlock.model.Booking;
import com.adlock.model.SalonRepot;
import com.adlock.repo.BookingServiceRepo;
import com.adlock.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {


    private final BookingServiceRepo bookingRepo;
    @Override
    public Booking createBooking(BookingRequest booking,
                                 UserDTO userDTO,
                                 SalonDto salonDto,
                                 Set<ServiceOfferingDto> servicedto) throws Exception {

     int totalDuration = 0;
     for(ServiceOfferingDto service: servicedto){
         totalDuration+= (int) service.getDuration();
     }
        LocalDateTime bookingStartTime = booking.getStartTime();
     LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);
 Boolean isSlotAvailable = isTimeSlotAvailable(salonDto,bookingStartTime, bookingEndTime);
 double totalPrice = 0;
 for(ServiceOfferingDto service: servicedto){
     totalPrice+=service.getPrice();
 }
 Set<Long> ids = new HashSet<>();
        for(ServiceOfferingDto service: servicedto){
            ids.add(service.getId());
        }
Booking newbooking = new Booking();
        newbooking.setCustomerId(userDTO.getId());
        newbooking.setSalonId(salonDto.getId());
        newbooking.setServiceIds(ids);
        newbooking.setStatus(BookingStatus.PENDING);
        newbooking.setStartTime(bookingStartTime);
        newbooking.setEndTime(bookingEndTime);
        newbooking.setTotalPrices((long) totalPrice);


        return bookingRepo.save(newbooking);
    }

    public Boolean isTimeSlotAvailable(SalonDto salonDto,
                                       LocalDateTime bookingStartTime,
                                       LocalDateTime bookingEndTime) throws Exception{
        List<Booking> existingBooking = getBookingsBySalon(salonDto.getId());
        LocalDateTime salonOpenTime = salonDto.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime = salonDto.getCloseTime().atDate(bookingStartTime.toLocalDate());
        if(bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isAfter(salonCloseTime)){
            throw new Exception("Booking time must be within salon's working hours ");
        }

        for(Booking existing : existingBooking){
            LocalDateTime existingBookingStartTime = existing.getStartTime();
            LocalDateTime existingBookingEndTime = existing.getEndTime();
            if(bookingStartTime.isBefore(existingBookingEndTime) && bookingEndTime.isAfter(existingBookingStartTime)){
                throw new Exception("slot not available, choose different time!");
            }

            if(bookingStartTime.isEqual(existingBookingStartTime) || bookingEndTime.isEqual(existingBookingEndTime)){
                throw new Exception("slot not available, choose different time!");
            }
        }
        return true;
    }

    @Override
    public List<Booking> getBokingsByCustomer(Long customerId) {
        return bookingRepo.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsBySalon(Long salonId) {
        return bookingRepo.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        Booking booking = bookingRepo.findById(id).orElse(null);
        if(booking == null){
            throw new Exception("Booking not found!");
        }
        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) throws Exception {
        Booking booking = getBookingById(bookingId);
        booking.setStatus(status);

        return bookingRepo.save(booking);
    }

    @Override
    public List<Booking> getBookingByDates(LocalDate date, Long salonId) {
        List<Booking> allBooking = getBookingsBySalon(salonId);
        if(date == null){
            return allBooking;
        }

        List<Booking> filterBooking = new ArrayList<Booking>();
        for(Booking booking: allBooking){
            if(isSameDate(booking.getStartTime(), date) || isSameDate(booking.getEndTime(), date) ){
                filterBooking.add(booking);
            }
        }
        return filterBooking;
    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date){
        return dateTime.toLocalDate().isEqual(date);
    }


    @Override
    public SalonRepot getSalonReport(long salonId) {
List<Booking> bookings = getBookingsBySalon(salonId);
   int  totalEarning = 0;
   for(Booking booking: bookings){
       totalEarning+=booking.getTotalPrices();
   }
Integer totalBooking = bookings.size();
   List<Booking> cancelledBooking  = new ArrayList<>();
   for(Booking booking: bookings){
       if(booking.getStatus().equals(BookingStatus.CANCELLED)){
           cancelledBooking.add(booking);
       }
   }
   int  totalRefund = 0;
   for(Booking booking: cancelledBooking){
       totalRefund+=booking.getTotalPrices();
   }
   SalonRepot repot = new SalonRepot();
   repot.setSalonId(salonId);
   repot.setCancelledBookings(cancelledBooking.size());
   repot.setTotalEarnings(totalEarning);
   repot.setTotalRefund((double) totalRefund);
   repot.setTotalBookings(totalBooking);


        return repot;
    }
}
