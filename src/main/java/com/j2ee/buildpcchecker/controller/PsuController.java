package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.PsuCreationRequest;
import com.j2ee.buildpcchecker.dto.request.PsuUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.PsuResponse;
import com.j2ee.buildpcchecker.service.PsuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/psus")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class PsuController {

    PsuService psuService;

    /**
     * Create a new PSU
     * POST /psus
     */
    @PostMapping
    public ApiResponse<PsuResponse> createPsu(@RequestBody @Valid PsuCreationRequest request) {
        log.info("Creating PSU: {}", request.getName());

        ApiResponse<PsuResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(psuService.createPsu(request));

        return apiResponse;
    }

    /**
     * Get all PSUs
     * GET /psus
     */
    @GetMapping
    public ApiResponse<List<PsuResponse>> getAllPsus() {
        log.info("Getting all PSUs");

        ApiResponse<List<PsuResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(psuService.getAllPsus());

        return apiResponse;
    }

    /**
     * Get PSU by ID
     * GET /psus/{id}
     */
    @GetMapping("/{id}")
    public ApiResponse<PsuResponse> getPsuById(@PathVariable("id") String psuId) {
        log.info("Getting PSU with ID: {}", psuId);

        return ApiResponse.<PsuResponse>builder()
                .result(psuService.getPsuById(psuId))
                .build();
    }

    /**
     * Update PSU by ID
     * PUT /psus/{id}
     */
    @PutMapping("/{id}")
    public ApiResponse<PsuResponse> updatePsu(
            @PathVariable("id") String psuId,
            @RequestBody @Valid PsuUpdateRequest request) {
        log.info("Updating PSU with ID: {}", psuId);

        ApiResponse<PsuResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(psuService.updatePsu(request, psuId));

        return apiResponse;
    }

    /**
     * Delete PSU by ID
     * DELETE /psus/{id}
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePsu(@PathVariable("id") String psuId) {
        log.info("Deleting PSU with ID: {}", psuId);

        psuService.deletePsu(psuId);

        return ApiResponse.<Void>builder()
                .message("PSU deleted successfully")
                .build();
    }
}
