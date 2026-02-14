package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.PcieConnectorRequest;
import com.j2ee.buildpcchecker.dto.response.PcieConnectorResponse;
import com.j2ee.buildpcchecker.entity.PcieConnector;
import com.j2ee.buildpcchecker.exception.AppException;
import com.j2ee.buildpcchecker.exception.ErrorCode;
import com.j2ee.buildpcchecker.mapper.PcieConnectorMapper;
import com.j2ee.buildpcchecker.repository.PcieConnectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class PcieConnectorService {

    PcieConnectorRepository pcieConnectorRepository;
    PcieConnectorMapper pcieConnectorMapper;

    public PcieConnectorResponse createPcieConnector(PcieConnectorRequest request) {
        log.info("Creating new PCIe Connector: {}", request.getId());

        if (pcieConnectorRepository.existsById(request.getId())) {
            throw new AppException(ErrorCode.PCIE_CONNECTOR_ALREADY_EXISTS);
        }

        PcieConnector pcieConnector = pcieConnectorMapper.toPcieConnector(request);
        PcieConnector savedPcieConnector = pcieConnectorRepository.save(pcieConnector);

        log.info("PCIe Connector created successfully with ID: {}", savedPcieConnector.getId());
        return pcieConnectorMapper.toPcieConnectorResponse(savedPcieConnector);
    }

    public List<PcieConnectorResponse> getAllPcieConnectors() {
        log.info("Getting all PCIe Connectors");
        List<PcieConnector> pcieConnectors = pcieConnectorRepository.findAll();
        return pcieConnectorMapper.toListPcieConnectorResponse(pcieConnectors);
    }

    public PcieConnectorResponse getPcieConnectorById(String pcieConnectorId) {
        log.info("Getting PCIe Connector with ID: {}", pcieConnectorId);

        PcieConnector pcieConnector = pcieConnectorRepository.findById(pcieConnectorId)
                .orElseThrow(() -> {
                    log.error("PCIe Connector not found with ID: {}", pcieConnectorId);
                    return new AppException(ErrorCode.PCIE_CONNECTOR_NOT_FOUND);
                });

        return pcieConnectorMapper.toPcieConnectorResponse(pcieConnector);
    }

    public PcieConnectorResponse updatePcieConnector(PcieConnectorRequest request, String pcieConnectorId) {
        log.info("Updating PCIe Connector with ID: {}", pcieConnectorId);

        PcieConnector pcieConnector = pcieConnectorRepository.findById(pcieConnectorId)
                .orElseThrow(() -> {
                    log.error("PCIe Connector not found with ID: {}", pcieConnectorId);
                    return new AppException(ErrorCode.PCIE_CONNECTOR_NOT_FOUND);
                });

        pcieConnectorMapper.updatePcieConnector(pcieConnector, request);
        PcieConnector updatedPcieConnector = pcieConnectorRepository.save(pcieConnector);

        log.info("PCIe Connector updated successfully with ID: {}", updatedPcieConnector.getId());
        return pcieConnectorMapper.toPcieConnectorResponse(updatedPcieConnector);
    }

    public void deletePcieConnector(String pcieConnectorId) {
        log.info("Deleting PCIe Connector with ID: {}", pcieConnectorId);

        PcieConnector pcieConnector = pcieConnectorRepository.findById(pcieConnectorId)
                .orElseThrow(() -> {
                    log.error("PCIe Connector not found with ID: {}", pcieConnectorId);
                    return new AppException(ErrorCode.PCIE_CONNECTOR_NOT_FOUND);
                });

        pcieConnectorRepository.delete(pcieConnector);
        log.info("PCIe Connector deleted successfully with ID: {}", pcieConnectorId);
    }
}
