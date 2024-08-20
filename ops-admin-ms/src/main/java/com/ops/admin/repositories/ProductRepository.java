package com.ops.admin.repositories;

import com.ops.admin.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product getByName (String name);
}
