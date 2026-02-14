package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.HddCreationRequest;
import com.j2ee.buildpcchecker.dto.request.HddUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.HddResponse;
import com.j2ee.buildpcchecker.service.HddService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hdds")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class HddController {

    HddService hddService;

    /**
     * Create a new HDD
     * POST /hdds
     */
    @PostMapping
    public ApiResponse<HddResponse> createHdd(@RequestBody @Valid HddCreationRequest request) {
        log.info("Creating HDD: {}", request.getName());

        ApiResponse<HddResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(hddService.createHdd(request));

        return apiResponse;
    }

    /**
     * Get all HDDs
     * GET /hdds
     */
    @GetMapping
    public ApiResponse<List<HddResponse>> getAllHdds() {
        log.info("Getting all HDDs");

        ApiResponse<List<HddResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(hddService.getAllHdds());

        return apiResponse;
    }

    /**
     * Get HDD by ID
     * GET /hdds/{id}
     */
    @GetMapping("/{id}")
    public ApiResponse<HddResponse> getHddById(@PathVariable("id") String hddId) {
        log.info("Getting HDD with ID: {}", hddId);

        return ApiResponse.<HddResponse>builder()
                .result(hddService.getHddById(hddId))
                .build();
    }

    /**
     * Update HDD by ID
     * PUT /hdds/{id}
     */
    @PutMapping("/{id}")
    public ApiResponse<HddResponse> updateHdd(
            @PathVariable("id") String hddId,
            @RequestBody @Valid HddUpdateRequest request) {
        log.info("Updating HDD with ID: {}", hddId);

        ApiResponse<HddResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(hddService.updateHdd(request, hddId));

        return apiResponse;
    }

    /**
     * Delete HDD by ID
     * DELETE /hdds/{id}
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteHdd(@PathVariable("id") String hddId) {
        log.info("Deleting HDD with ID: {}", hddId);

        hddService.deleteHdd(hddId);

        return ApiResponse.<Void>builder()
                .message("HDD deleted successfully")
                .build();
    }
}
