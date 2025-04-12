package com.adlock.service;

import com.adlock.model.Salon;
import com.adlock.payload.dto.SalonDTO;
import com.adlock.payload.dto.UserDTO;

import java.util.List;

public interface SalonService {
Salon createSalon(SalonDTO salon, UserDTO user);
Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception;
List<Salon> getAllSalons();
Salon getSalonById(Long salonId) throws Exception;
Salon getSalonByOwnerId(Long ownerId);
List<Salon> searchSalonByCity(String city);

}
