package com.ecommerce.back.application.service;


import com.ecommerce.back.domain.model.BaseReference;
import com.ecommerce.back.domain.ports.BaseReferenceRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseReferenceService {

    private final BaseReferenceRepositoryPort repository;

    public BaseReference create(BaseReference baseReference) {
        baseReference.setCreatedAt(java.time.LocalDateTime.now());
        baseReference.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(baseReference);
    }

    public BaseReference update(BaseReference baseReference) {
        BaseReference toUpdate = findById(baseReference.getId())
                .orElseThrow(() -> new RuntimeException("Id not found"));

        toUpdate.setName(baseReference.getName());
        toUpdate.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(toUpdate);
    }

    public List<BaseReference> findAll() {
        return repository.findAll();
    }

    public Optional<BaseReference> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
