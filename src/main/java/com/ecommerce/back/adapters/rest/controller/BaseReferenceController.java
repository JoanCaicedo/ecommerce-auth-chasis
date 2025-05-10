package com.ecommerce.back.adapters.rest.controller;

import com.ecommerce.back.adapters.rest.dto.BaseReferenceRequestDTO;
import com.ecommerce.back.adapters.rest.dto.BaseReferenceResponseDTO;
import com.ecommerce.back.adapters.rest.mapper.BaseReferenceMapper;
import com.ecommerce.back.application.service.BaseReferenceService;
import com.ecommerce.back.domain.model.BaseReference;
import com.ecommerce.back.shared.validation.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/basereference")
@RequiredArgsConstructor
public class BaseReferenceController {

    private final BaseReferenceService service;

    @GetMapping
    public List<BaseReferenceResponseDTO> getAll() {
        return service.findAll().stream()
                .map(BaseReferenceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            BaseReference baseReference = service.findById(id)
                    .orElseThrow(() -> new RuntimeException("BaseReference with id " + id + " not found"));

            BaseReferenceResponseDTO response = BaseReferenceMapper.toResponseDTO(baseReference);
            return ResponseEntity.ok(response);

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody BaseReferenceRequestDTO request) {
        try {
            RequestValidator validator = new RequestValidator();
            validator.validate(request.getName(), "name")
                    .isEmpty()
                    .maxLength();

            if (!validator.getError().isEmpty()) {
                throw new RuntimeException(validator.getError());
            }

            BaseReference model = BaseReferenceMapper.toModel(request);
            BaseReference saved = service.create(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(BaseReferenceMapper.toResponseDTO(saved));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BaseReferenceRequestDTO request) {
        try {
            BaseReference existing = service.findById(id)
                    .orElseThrow(() -> new RuntimeException("BaseReference with id " + id + " not found"));

            RequestValidator validator = new RequestValidator();
            validator.validate(request.getName(), "name")
                    .isEmpty()
                    .maxLength();

            if (!validator.getError().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", validator.getError()));
            }

            BaseReference updatedModel = BaseReferenceMapper.toModel(request);
            updatedModel.setId(existing.getId());
            BaseReference updated = service.update(updatedModel);

            return ResponseEntity.ok(BaseReferenceMapper.toResponseDTO(updated));

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.findById(id)
                    .orElseThrow(() -> new RuntimeException("BaseReference with id " + id + " not found"));

            service.delete(id);
            return ResponseEntity.noContent().build();

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
        }
    }
}

