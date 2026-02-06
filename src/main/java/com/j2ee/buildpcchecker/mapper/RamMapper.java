package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.RamCreationRequest;
import com.j2ee.buildpcchecker.dto.request.RamUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.RamResponse;
import com.j2ee.buildpcchecker.entity.Ram;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RamMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ramType", ignore = true)
    Ram toRam(RamCreationRequest request);

    RamResponse toRamResponse(Ram ram);

    List<RamResponse> toListRamResponse(List<Ram> rams);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ramType", ignore = true)
    void updateRam(@MappingTarget Ram ram, RamUpdateRequest request);
}

