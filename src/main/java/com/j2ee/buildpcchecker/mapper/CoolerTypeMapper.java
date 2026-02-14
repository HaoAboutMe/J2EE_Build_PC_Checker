package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.CoolerTypeRequest;
import com.j2ee.buildpcchecker.dto.response.CoolerTypeResponse;
import com.j2ee.buildpcchecker.entity.CoolerType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoolerTypeMapper {

    CoolerType toCoolerType(CoolerTypeRequest request);

    CoolerTypeResponse toCoolerTypeResponse(CoolerType coolerType);

    List<CoolerTypeResponse> toListCoolerTypeResponse(List<CoolerType> coolerTypes);

    void updateCoolerType(@MappingTarget CoolerType coolerType, CoolerTypeRequest request);
}
