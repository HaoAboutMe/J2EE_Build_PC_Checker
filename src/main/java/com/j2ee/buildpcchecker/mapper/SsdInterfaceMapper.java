package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.SsdInterfaceRequest;
import com.j2ee.buildpcchecker.dto.response.SsdInterfaceResponse;
import com.j2ee.buildpcchecker.entity.SsdInterface;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SsdInterfaceMapper {

    SsdInterface toSsdInterface(SsdInterfaceRequest request);

    SsdInterfaceResponse toSsdInterfaceResponse(SsdInterface ssdInterface);

    List<SsdInterfaceResponse> toListSsdInterfaceResponse(List<SsdInterface> ssdInterfaces);

    void updateSsdInterface(@MappingTarget SsdInterface ssdInterface, SsdInterfaceRequest request);
}
