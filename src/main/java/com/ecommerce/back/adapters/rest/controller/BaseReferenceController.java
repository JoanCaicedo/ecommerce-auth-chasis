package com.ecommerce.back.adapters.rest.controller;

import com.ecommerce.back.adapters.rest.dto.BaseReferenceRequestDTO;
import com.ecommerce.back.adapters.rest.dto.BaseReferenceResponseDTO;
import com.ecommerce.back.adapters.rest.mapper.BaseReferenceMapper;
import com.ecommerce.back.application.service.BaseReferenceService;
import com.ecommerce.back.domain.model.BaseReference;
import com.ecommerce.back.shared.validation.RequestValidator;
import lombok.RequiredArgsConstructor;
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
    public BaseReferenceResponseDTO findById(@PathVariable Long id) {
        return service.findById(id)
                .map(BaseReferenceMapper::toResponseDTO)
                .orElseThrow(()-> new RuntimeException("Not found"));
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
            return ResponseEntity.ok(BaseReferenceMapper.toResponseDTO(saved));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

