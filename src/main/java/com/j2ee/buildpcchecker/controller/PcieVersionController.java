package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.PcieVersionRequest;
import com.j2ee.buildpcchecker.dto.response.PcieVersionResponse;
import com.j2ee.buildpcchecker.service.PcieVersionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pcie-versions")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class PcieVersionController {

    PcieVersionService pcieVersionService;

    /**
     * Create a new PCIe Version
     * POST /pcie-versions
     */
    @PostMapping
    public ApiResponse<PcieVersionResponse> createPcieVersion(@RequestBody @Valid PcieVersionRequest request) {
        log.info("Creating PCIe Version: {}", request.getId());

        ApiResponse<PcieVersionResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(pcieVersionService.createPcieVersion(request));

        return apiResponse;
    }

    /**
     * Get all PCIe Versions
     * GET /pcie-versions
     */
    @GetMapping
    public ApiResponse<List<PcieVersionResponse>> getAllPcieVersions() {
        log.info("Getting all PCIe Versions");

        ApiResponse<List<PcieVersionResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(pcieVersionService.getAllPcieVersions());

        return apiResponse;
    }

    /**
     * Get PCIe Version by ID
     * GET /pcie-versions/{id}
     */
    @GetMapping("/{id}")
    public ApiResponse<PcieVersionResponse> getPcieVersionById(@PathVariable("id") String pcieVersionId) {
        log.info("Getting PCIe Version with ID: {}", pcieVersionId);

        return ApiResponse.<PcieVersionResponse>builder()
                .result(pcieVersionService.getPcieVersionById(pcieVersionId))
                .build();
    }

    /**
     * Update PCIe Version by ID
     * PUT /pcie-versions/{id}
     */
    @PutMapping("/{id}")
    public ApiResponse<PcieVersionResponse> updatePcieVersion(
            @PathVariable("id") String pcieVersionId,
            @RequestBody @Valid PcieVersionRequest request) {
        log.info("Updating PCIe Version with ID: {}", pcieVersionId);

        ApiResponse<PcieVersionResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(pcieVersionService.updatePcieVersion(request, pcieVersionId));

        return apiResponse;
    }

    /**
     * Delete PCIe Version by ID
     * DELETE /pcie-versions/{id}
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePcieVersion(@PathVariable("id") String pcieVersionId) {
        log.info("Deleting PCIe Version with ID: {}", pcieVersionId);

        pcieVersionService.deletePcieVersion(pcieVersionId);

        return ApiResponse.<Void>builder()
                .message("PCIe Version deleted successfully")
                .build();
    }
}

