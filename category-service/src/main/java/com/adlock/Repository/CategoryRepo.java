package com.adlock.Repository;

import com.adlock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    Set<Category> findBySalonId(long salonId);
}
