package com.adlock.mapper;

import com.adlock.model.Salon;
import com.adlock.payload.dto.SalonDTO;

public class SalonMapper {

    public static SalonDTO mapToDto(Salon salon){
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(salon.getId());
        salonDTO.setName(salon.getName());
        salonDTO.setEmail(salon.getEmail());
        salonDTO.setAddress(salon.getAddress());
        salonDTO.setCity(salon.getCity());
        salonDTO.setCloseTime(salon.getCloseTime());
        salonDTO.setOpenTime(salon.getOpenTime());
        salonDTO.setImages(salon.getImages());
        salonDTO.setPhoneNumber(salon.getPhoneNumber());
        salonDTO.setOwnerId(salon.getOwnerId());
        return salonDTO;
    }
}
