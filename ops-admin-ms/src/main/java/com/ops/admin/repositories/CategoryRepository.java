package com.ops.admin.repositories;

import com.ops.admin.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getByName(String name);
}
