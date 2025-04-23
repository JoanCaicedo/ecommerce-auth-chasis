package com.ecommerce.back.adapters.rest.mapper;

import com.ecommerce.back.adapters.rest.dto.BaseReferenceRequestDTO;
import com.ecommerce.back.adapters.rest.dto.BaseReferenceResponseDTO;
import com.ecommerce.back.domain.model.BaseReference;

import java.time.LocalDateTime;

public class BaseReferenceMapper {

    public static BaseReference toModel(BaseReferenceRequestDTO dto){

        return BaseReference.builder()
                .name(dto.getName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now()).build();
    }

    public static BaseReferenceResponseDTO toResponseDTO (BaseReference model){
        return BaseReferenceResponseDTO.builder()
                .id(model.getId())
                .name(model.getName())
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
                .build();
    }
}
