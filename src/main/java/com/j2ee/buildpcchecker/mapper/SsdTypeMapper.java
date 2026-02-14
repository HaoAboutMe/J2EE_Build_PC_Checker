package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.SsdTypeRequest;
import com.j2ee.buildpcchecker.dto.response.SsdTypeResponse;
import com.j2ee.buildpcchecker.entity.SsdType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SsdTypeMapper {

    SsdType toSsdType(SsdTypeRequest request);

    SsdTypeResponse toSsdTypeResponse(SsdType ssdType);

    List<SsdTypeResponse> toListSsdTypeResponse(List<SsdType> ssdTypes);

    void updateSsdType(@MappingTarget SsdType ssdType, SsdTypeRequest request);
}
