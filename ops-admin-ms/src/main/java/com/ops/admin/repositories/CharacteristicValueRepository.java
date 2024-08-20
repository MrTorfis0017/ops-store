package com.ops.admin.repositories;

import com.ops.admin.entities.CharacteristicValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacteristicValueRepository extends JpaRepository<CharacteristicValue,Long> {
}
