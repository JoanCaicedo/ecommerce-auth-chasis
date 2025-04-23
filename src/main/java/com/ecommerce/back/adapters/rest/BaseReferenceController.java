package com.ecommerce.back.adapters.rest;

import com.ecommerce.back.adapters.rest.dto.BaseReferenceRequestDTO;
import com.ecommerce.back.adapters.rest.dto.BaseReferenceResponseDTO;
import com.ecommerce.back.adapters.rest.mapper.BaseReferenceMapper;
import com.ecommerce.back.application.service.BaseReferenceService;
import com.ecommerce.back.domain.model.BaseReference;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public BaseReferenceResponseDTO create(@RequestBody BaseReferenceRequestDTO request) {
        BaseReference model = BaseReferenceMapper.toModel(request);
        BaseReference saved = service.create(model);
        return BaseReferenceMapper.toResponseDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

