package com.ecommerce.back.infrastructure.repository;

import com.ecommerce.back.infrastructure.repository.entity.BaseReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBaseReferenceRepository extends JpaRepository<BaseReferenceEntity, Long> {
}
