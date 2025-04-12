package com.adlock.service;

import com.adlock.model.Salon;
import com.adlock.payload.dto.SalonDTO;
import com.adlock.payload.dto.UserDTO;
import com.adlock.repository.SalonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService{

    private final SalonRepository salonRepository;
    @Override
    // in the request dto will use to that data will come from dto object and then save in the salon entity table dto here treat like req frame
    public Salon createSalon(SalonDTO req, UserDTO user) {
     Salon salon = new Salon();
     salon.setName(req.getName());
     salon.setCity(req.getCity());
     salon.setEmail(req.getEmail());
     salon.setImages(req.getImages());
     salon.setAddress(req.getAddress());
     salon.setOpenTime(req.getOpenTime());
     salon.setCloseTime(req.getCloseTime());
     salon.setOwnerId(user.getId());
     salon.setPhoneNumber(req.getPhoneNumber());



        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception {
        Salon exsalon = salonRepository.findById(salonId).orElse(null);
        if(exsalon != null & salon.getOwnerId().equals(user.getId())){
            exsalon.setName(salon.getName());
            exsalon.setCity(salon.getCity());
            exsalon.setAddress(salon.getAddress());
            exsalon.setOpenTime(salon.getOpenTime());
            exsalon.setCloseTime(salon.getCloseTime());
            exsalon.setPhoneNumber(salon.getPhoneNumber());
            exsalon.setImages(salon.getImages());
            exsalon.setEmail(salon.getEmail());
            exsalon.setOwnerId(user.getId());

            return salonRepository.save(exsalon);
        }

       throw new Exception("salon not exist");
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) throws Exception{
        Salon salon = salonRepository.findById(salonId).orElse(null);
        if(salon == null){
            throw new Exception("salon not exist");
        }
        return salon;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {

           return salonRepository.searchSalon(city);

    }
}
