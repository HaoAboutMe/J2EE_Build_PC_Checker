package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.CpuCreationRequest;
import com.j2ee.buildpcchecker.dto.request.CpuUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.CpuResponse;
import com.j2ee.buildpcchecker.entity.Cpu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CpuMapper {

    @Mapping(target = "socket", ignore = true)
    @Mapping(target = "pcieVersion", ignore = true)
    Cpu toCpu(CpuCreationRequest request);

    CpuResponse toCpuResponse(Cpu cpu);

    List<CpuResponse> toListCpuResponse(List<Cpu> cpus);

    @Mapping(target = "socket", ignore = true)
    @Mapping(target = "pcieVersion", ignore = true)
    void updateCpu(@MappingTarget Cpu cpu, CpuUpdateRequest request);
}

