package com.ops.admin.repositories;

import com.ops.admin.entities.SupplyItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyItemRepository extends JpaRepository<SupplyItem,Long> {
}
