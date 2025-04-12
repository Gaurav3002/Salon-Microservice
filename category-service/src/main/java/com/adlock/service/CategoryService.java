package com.adlock.service;

import com.adlock.dto.SalonDTO;
import com.adlock.model.Category;
import java.util.List;
import  java.util.Set;

public interface CategoryService {

    Category saveCategory(Category category, SalonDTO salonDTO);
    Set<Category> getAllCategoryBySalon(long id);
    Category getCategoryById(Long id)throws Exception;

    void deleteCategoryById(Long id, Long salonId) throws Exception;

}
