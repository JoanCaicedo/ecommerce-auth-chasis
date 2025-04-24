package com.ecommerce.back.domain.ports;


import com.ecommerce.back.domain.model.BaseReference;

import java.util.List;
import java.util.Optional;

public interface BaseReferenceRepositoryPort {

    BaseReference save(BaseReference baseReference);
    List<BaseReference> findAll();
    Optional<BaseReference> findById(Long id);
    void deleteById(Long id);

}

