package com.adlock.service;

import com.adlock.Repository.ServiceOfferingRepo;
import com.adlock.dto.CategoryDto;
import com.adlock.dto.SalonDto;
import com.adlock.dto.ServiceOfferingDto;
import com.adlock.model.ServiceOffering;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService{

    private final ServiceOfferingRepo serviceOfferingRepo;

    @Override
    public ServiceOffering createService(SalonDto salonDto, ServiceOfferingDto serviceOfferingDto, CategoryDto categoryDto) {
        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setName(serviceOfferingDto.getName());
        serviceOffering.setImage(serviceOfferingDto.getImage());
        serviceOffering.setDuration(serviceOfferingDto.getDuration());
        serviceOffering.setDescription(serviceOfferingDto.getDescription());
        serviceOffering.setPrice(serviceOfferingDto.getPrice());
        serviceOffering.setCategoryId(categoryDto.getId());
        serviceOffering.setSalonId(salonDto.getId());
        return serviceOfferingRepo.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception {
      ServiceOffering serviceOffering = serviceOfferingRepo.findById(serviceId).orElse(null);
      if(serviceOffering==null){
          throw new Exception("service not found with this id");
      }
        serviceOffering.setName(service.getName());
        serviceOffering.setImage(service.getImage());
        serviceOffering.setDuration(service.getDuration());
        serviceOffering.setDescription(service.getDescription());
        serviceOffering.setPrice(service.getPrice());
        return serviceOfferingRepo.save(serviceOffering);
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId) {
        Set<ServiceOffering> serviceOffering = serviceOfferingRepo.findBySalonId(salonId);
        if(categoryId!= null){
            serviceOffering= serviceOffering.stream().filter((service) ->service.getCategoryId()!=null &&
            service.getCategoryId()==categoryId).collect(Collectors.toSet());

        }
        return serviceOffering;
    }

    @Override
    public Set<ServiceOffering> getServiceByIds(Set<Long> ids) {

         List<ServiceOffering> services = serviceOfferingRepo.findAllById(ids);
         return new HashSet<>(services);
    }

    @Override
    public ServiceOffering getServiceById(Long id) throws Exception{
        ServiceOffering serviceOffering = serviceOfferingRepo.findById(id).orElse(null);
        if(serviceOffering == null){
            throw new Exception("service not exist with this id "+ id);
        }
        return serviceOffering;
    }
}
