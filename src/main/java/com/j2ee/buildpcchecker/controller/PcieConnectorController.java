package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.PcieConnectorRequest;
import com.j2ee.buildpcchecker.dto.response.PcieConnectorResponse;
import com.j2ee.buildpcchecker.service.PcieConnectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pcie-connectors")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PcieConnectorController {

    PcieConnectorService pcieConnectorService;

    @PostMapping
    public ApiResponse<PcieConnectorResponse> createPcieConnector(@RequestBody @Valid PcieConnectorRequest request) {
        log.info("POST /pcie-connectors - Creating new PCIe Connector");
        return ApiResponse.<PcieConnectorResponse>builder()
                .result(pcieConnectorService.createPcieConnector(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<PcieConnectorResponse>> getAllPcieConnectors() {
        log.info("GET /pcie-connectors - Getting all PCIe Connectors");
        return ApiResponse.<List<PcieConnectorResponse>>builder()
                .result(pcieConnectorService.getAllPcieConnectors())
                .build();
    }

    @GetMapping("/{pcieConnectorId}")
    public ApiResponse<PcieConnectorResponse> getPcieConnectorById(@PathVariable String pcieConnectorId) {
        log.info("GET /pcie-connectors/{} - Getting PCIe Connector by ID", pcieConnectorId);
        return ApiResponse.<PcieConnectorResponse>builder()
                .result(pcieConnectorService.getPcieConnectorById(pcieConnectorId))
                .build();
    }

    @PutMapping("/{pcieConnectorId}")
    public ApiResponse<PcieConnectorResponse> updatePcieConnector(
            @PathVariable String pcieConnectorId,
            @RequestBody @Valid PcieConnectorRequest request) {
        log.info("PUT /pcie-connectors/{} - Updating PCIe Connector", pcieConnectorId);
        return ApiResponse.<PcieConnectorResponse>builder()
                .result(pcieConnectorService.updatePcieConnector(request, pcieConnectorId))
                .build();
    }

    @DeleteMapping("/{pcieConnectorId}")
    public ApiResponse<String> deletePcieConnector(@PathVariable String pcieConnectorId) {
        log.info("DELETE /pcie-connectors/{} - Deleting PCIe Connector", pcieConnectorId);
        pcieConnectorService.deletePcieConnector(pcieConnectorId);
        return ApiResponse.<String>builder()
                .result("PCIe Connector deleted successfully")
                .build();
    }
}
