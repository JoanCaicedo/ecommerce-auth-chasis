package com.ecommerce.back.infrastructure.repository.adapter;

import com.ecommerce.back.domain.model.BaseReference;
import com.ecommerce.back.domain.ports.BaseReferenceRepositoryPort;
import com.ecommerce.back.infrastructure.repository.entity.BaseReferenceEntity;
import com.ecommerce.back.infrastructure.repository.JpaBaseReferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BaseReferencePersistenceAdapter implements BaseReferenceRepositoryPort {

    private final JpaBaseReferenceRepository repository;

    /**
     * @param baseReference
     * @return
     */
    @Override
    public BaseReference save(BaseReference baseReference) {
        BaseReferenceEntity entity = ToEntity(baseReference);
        BaseReferenceEntity saved = repository.save(entity);
        return ToModel(saved);
    }

    /**
     * @return
     */
    @Override
    public List<BaseReference> findAll() {
        return repository.findAll().stream().map(this::ToModel).collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<BaseReference> findById(Long id) {
        return repository.findById(id).map(this::ToModel);
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }

    private BaseReference ToModel(BaseReferenceEntity entity) {
        return BaseReference.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private BaseReferenceEntity ToEntity(BaseReference model) {
        return BaseReferenceEntity.builder()
                .id(model.getId())
                .name(model.getName())
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
                .build();
    }
}
