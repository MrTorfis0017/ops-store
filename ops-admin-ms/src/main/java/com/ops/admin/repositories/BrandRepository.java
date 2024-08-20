package com.ops.admin.repositories;

import com.ops.admin.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand getByName(String name);
}
