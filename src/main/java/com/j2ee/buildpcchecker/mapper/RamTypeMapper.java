package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.RamTypeRequest;
import com.j2ee.buildpcchecker.dto.response.RamTypeResponse;
import com.j2ee.buildpcchecker.entity.RamType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RamTypeMapper {

    RamType toRamType(RamTypeRequest request);

    RamTypeResponse toRamTypeResponse(RamType ramType);

    List<RamTypeResponse> toListRamTypeResponse(List<RamType> ramTypes);

    void updateRamType(@MappingTarget RamType ramType, RamTypeRequest request);
}

