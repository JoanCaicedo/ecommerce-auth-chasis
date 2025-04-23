package com.ecommerce.back.infrastructure.repository;


import com.ecommerce.back.domain.model.BaseReference;
import com.ecommerce.back.domain.ports.BaseReferenceRepositoryPort;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
//@Table(name = "base_reference")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class BaseReferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

@Repository
interface SpringBaseReferenceRepository extends JpaRepository<BaseReferenceEntity, Long> {

}

@Component
@RequiredArgsConstructor
public class BaseReferenceJpaRepository implements BaseReferenceRepositoryPort {

    private final SpringBaseReferenceRepository repository;

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