package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.PcieConnectorRequest;
import com.j2ee.buildpcchecker.dto.response.PcieConnectorResponse;
import com.j2ee.buildpcchecker.entity.PcieConnector;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PcieConnectorMapper {

    PcieConnector toPcieConnector(PcieConnectorRequest request);

    PcieConnectorResponse toPcieConnectorResponse(PcieConnector pcieConnector);

    List<PcieConnectorResponse> toListPcieConnectorResponse(List<PcieConnector> pcieConnectors);

    void updatePcieConnector(@MappingTarget PcieConnector pcieConnector, PcieConnectorRequest request);
}
