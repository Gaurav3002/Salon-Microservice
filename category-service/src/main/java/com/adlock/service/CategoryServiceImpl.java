package com.adlock.service;

import com.adlock.Repository.CategoryRepo;
import com.adlock.dto.SalonDTO;
import com.adlock.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepo categoryRepo;

    @Override
    public Category saveCategory(Category category, SalonDTO salonDTO) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setImage(category.getImage());
        newCategory.setSalonId(salonDTO.getId());
        return categoryRepo.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoryBySalon(long id) {
        return categoryRepo.findBySalonId(id);
    }


    @Override
    public Category getCategoryById(Long id) throws Exception {
       Category category = categoryRepo.findById(id).orElse(null);
       if(category == null){
           throw new Exception("category not exist with id"+ id);
       }
       return category;
    }

    @Override
    public void deleteCategoryById(Long id, Long  salonId) throws Exception {
     Category category = getCategoryById(id);
     if(!category.getSalonId().equals(salonId)){
         throw new Exception("you don't have permission to delete this Category");
     }
  categoryRepo.deleteById(id);
    }
}
