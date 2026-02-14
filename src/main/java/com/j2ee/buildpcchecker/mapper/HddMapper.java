package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.HddCreationRequest;
import com.j2ee.buildpcchecker.dto.request.HddUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.HddResponse;
import com.j2ee.buildpcchecker.entity.Hdd;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HddMapper {

    @Mapping(target = "formFactor", ignore = true) // Will be set manually in service
    @Mapping(target = "interfaceType", ignore = true) // Will be set manually in service
    @Mapping(target = "id", ignore = true)
    Hdd toHdd(HddCreationRequest request);

    HddResponse toHddResponse(Hdd hdd);

    List<HddResponse> toListHddResponse(List<Hdd> hdds);

    @Mapping(target = "formFactor", ignore = true) // Will be set manually in service if needed
    @Mapping(target = "interfaceType", ignore = true) // Will be set manually in service if needed
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    void updateHdd(@MappingTarget Hdd hdd, HddUpdateRequest request);
}
