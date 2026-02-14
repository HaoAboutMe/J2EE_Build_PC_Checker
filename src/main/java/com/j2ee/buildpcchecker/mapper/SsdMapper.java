package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.SsdCreationRequest;
import com.j2ee.buildpcchecker.dto.request.SsdUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.SsdResponse;
import com.j2ee.buildpcchecker.entity.Ssd;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SsdMapper {

    @Mapping(target = "formFactor", ignore = true) // Will be set manually in service
    @Mapping(target = "ssdType", ignore = true) // Will be set manually in service
    @Mapping(target = "interfaceType", ignore = true) // Will be set manually in service
    @Mapping(target = "id", ignore = true)
    Ssd toSsd(SsdCreationRequest request);

    SsdResponse toSsdResponse(Ssd ssd);

    List<SsdResponse> toListSsdResponse(List<Ssd> ssds);
    
    @Mapping(target = "formFactor", ignore = true) // Will be set manually in service if needed
    @Mapping(target = "ssdType", ignore = true) // Will be set manually in service if needed
    @Mapping(target = "interfaceType", ignore = true) // Will be set manually in service if needed
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    void updateSsd(@MappingTarget Ssd ssd, SsdUpdateRequest request);
}
