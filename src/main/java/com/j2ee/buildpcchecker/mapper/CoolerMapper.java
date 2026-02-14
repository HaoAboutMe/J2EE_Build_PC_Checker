package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.CoolerCreationRequest;
import com.j2ee.buildpcchecker.dto.request.CoolerUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.CoolerResponse;
import com.j2ee.buildpcchecker.entity.Cooler;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoolerMapper {

    @Mapping(target = "coolerType", ignore = true) // Will be set manually in service
    @Mapping(target = "id", ignore = true)
    Cooler toCooler(CoolerCreationRequest request);

    CoolerResponse toCoolerResponse(Cooler cooler);

    List<CoolerResponse> toListCoolerResponse(List<Cooler> coolers);
    
    @Mapping(target = "coolerType", ignore = true) // Will be set manually in service if needed
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    void updateCooler(@MappingTarget Cooler cooler, CoolerUpdateRequest request);
}
