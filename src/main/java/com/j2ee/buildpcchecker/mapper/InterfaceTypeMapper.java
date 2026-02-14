package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.InterfaceTypeRequest;
import com.j2ee.buildpcchecker.dto.response.InterfaceTypeResponse;
import com.j2ee.buildpcchecker.entity.InterfaceType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InterfaceTypeMapper {

    InterfaceType toInterfaceType(InterfaceTypeRequest request);

    InterfaceTypeResponse toInterfaceTypeResponse(InterfaceType interfaceType);

    List<InterfaceTypeResponse> toListInterfaceTypeResponse(List<InterfaceType> interfaceTypes);

    void updateInterfaceType(@MappingTarget InterfaceType interfaceType, InterfaceTypeRequest request);
}

