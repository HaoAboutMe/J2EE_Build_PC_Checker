package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.SsdCreationRequest;
import com.j2ee.buildpcchecker.dto.request.SsdUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.SsdResponse;
import com.j2ee.buildpcchecker.service.SsdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ssds")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class SsdController {

    SsdService ssdService;

    /**
     * Create a new SSD
     * POST /ssds
     */
    @PostMapping
    public ApiResponse<SsdResponse> createSsd(@RequestBody @Valid SsdCreationRequest request) {
        log.info("Creating SSD: {}", request.getName());

        ApiResponse<SsdResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ssdService.createSsd(request));

        return apiResponse;
    }

    /**
     * Get all SSDs
     * GET /ssds
     */
    @GetMapping
    public ApiResponse<List<SsdResponse>> getAllSsds() {
        log.info("Getting all SSDs");

        ApiResponse<List<SsdResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ssdService.getAllSsds());

        return apiResponse;
    }

    /**
     * Get SSD by ID
     * GET /ssds/{id}
     */
    @GetMapping("/{id}")
    public ApiResponse<SsdResponse> getSsdById(@PathVariable("id") String ssdId) {
        log.info("Getting SSD with ID: {}", ssdId);

        return ApiResponse.<SsdResponse>builder()
                .result(ssdService.getSsdById(ssdId))
                .build();
    }

    /**
     * Update SSD by ID
     * PUT /ssds/{id}
     */
    @PutMapping("/{id}")
    public ApiResponse<SsdResponse> updateSsd(
            @PathVariable("id") String ssdId,
            @RequestBody @Valid SsdUpdateRequest request) {
        log.info("Updating SSD with ID: {}", ssdId);

        ApiResponse<SsdResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ssdService.updateSsd(request, ssdId));

        return apiResponse;
    }

    /**
     * Delete SSD by ID
     * DELETE /ssds/{id}
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteSsd(@PathVariable("id") String ssdId) {
        log.info("Deleting SSD with ID: {}", ssdId);

        ssdService.deleteSsd(ssdId);

        return ApiResponse.<Void>builder()
                .message("SSD deleted successfully")
                .build();
    }
}
