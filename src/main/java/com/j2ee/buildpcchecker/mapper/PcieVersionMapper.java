package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.PcieVersionRequest;
import com.j2ee.buildpcchecker.dto.response.PcieVersionResponse;
import com.j2ee.buildpcchecker.entity.PcieVersion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PcieVersionMapper {

    PcieVersion toPcieVersion(PcieVersionRequest request);

    PcieVersionResponse toPcieVersionResponse(PcieVersion pcieVersion);

    List<PcieVersionResponse> toListPcieVersionResponse(List<PcieVersion> pcieVersions);

    void updatePcieVersion(@MappingTarget PcieVersion pcieVersion, PcieVersionRequest request);
}

