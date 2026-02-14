package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.HddInterfaceRequest;
import com.j2ee.buildpcchecker.dto.response.HddInterfaceResponse;
import com.j2ee.buildpcchecker.entity.HddInterface;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HddInterfaceMapper {

    HddInterface toHddInterface(HddInterfaceRequest request);

    HddInterfaceResponse toHddInterfaceResponse(HddInterface hddInterface);

    List<HddInterfaceResponse> toListHddInterfaceResponse(List<HddInterface> hddInterfaces);

    void updateHddInterface(@MappingTarget HddInterface hddInterface, HddInterfaceRequest request);
}
