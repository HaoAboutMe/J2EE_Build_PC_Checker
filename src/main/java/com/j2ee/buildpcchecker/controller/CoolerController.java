package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.CoolerCreationRequest;
import com.j2ee.buildpcchecker.dto.request.CoolerUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.CoolerResponse;
import com.j2ee.buildpcchecker.service.CoolerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/coolers")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CoolerController {

    CoolerService coolerService;

    /**
     * Create a new Cooler
     * POST /coolers
     */
    @PostMapping
    public ApiResponse<CoolerResponse> createCooler(@RequestBody @Valid CoolerCreationRequest request) {
        log.info("Creating Cooler: {}", request.getName());

        ApiResponse<CoolerResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(coolerService.createCooler(request));

        return apiResponse;
    }

    /**
     * Get all Coolers
     * GET /coolers
     */
    @GetMapping
    public ApiResponse<List<CoolerResponse>> getAllCoolers() {
        log.info("Getting all Coolers");

        ApiResponse<List<CoolerResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(coolerService.getAllCoolers());

        return apiResponse;
    }

    /**
     * Get Cooler by ID
     * GET /coolers/{id}
     */
    @GetMapping("/{id}")
    public ApiResponse<CoolerResponse> getCoolerById(@PathVariable("id") String coolerId) {
        log.info("Getting Cooler with ID: {}", coolerId);

        return ApiResponse.<CoolerResponse>builder()
                .result(coolerService.getCoolerById(coolerId))
                .build();
    }

    /**
     * Update Cooler by ID
     * PUT /coolers/{id}
     */
    @PutMapping("/{id}")
    public ApiResponse<CoolerResponse> updateCooler(
            @PathVariable("id") String coolerId,
            @RequestBody @Valid CoolerUpdateRequest request) {
        log.info("Updating Cooler with ID: {}", coolerId);

        ApiResponse<CoolerResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(coolerService.updateCooler(request, coolerId));

        return apiResponse;
    }

    /**
     * Delete Cooler by ID
     * DELETE /coolers/{id}
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCooler(@PathVariable("id") String coolerId) {
        log.info("Deleting Cooler with ID: {}", coolerId);

        coolerService.deleteCooler(coolerId);

        return ApiResponse.<Void>builder()
                .message("Cooler deleted successfully")
                .build();
    }
}
