package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.RamCreationRequest;
import com.j2ee.buildpcchecker.dto.request.RamUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.RamResponse;
import com.j2ee.buildpcchecker.service.RamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rams")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RamController {

    RamService ramService;

    /**
     * Create a new RAM
     * POST /rams
     */
    @PostMapping
    public ApiResponse<RamResponse> createRam(@RequestBody @Valid RamCreationRequest request) {
        log.info("Creating RAM: {}", request.getName());

        ApiResponse<RamResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ramService.createRam(request));

        return apiResponse;
    }

    /**
     * Get all RAMs
     * GET /rams
     */
    @GetMapping
    public ApiResponse<List<RamResponse>> getAllRams() {
        log.info("Getting all RAMs");

        ApiResponse<List<RamResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ramService.getAllRams());

        return apiResponse;
    }

    /**
     * Get RAM by ID
     * GET /rams/{id}
     */
    @GetMapping("/{id}")
    public ApiResponse<RamResponse> getRamById(@PathVariable("id") String ramId) {
        log.info("Getting RAM with ID: {}", ramId);

        return ApiResponse.<RamResponse>builder()
                .result(ramService.getRamById(ramId))
                .build();
    }

    /**
     * Update RAM by ID
     * PUT /rams/{id}
     */
    @PutMapping("/{id}")
    public ApiResponse<RamResponse> updateRam(
            @PathVariable("id") String ramId,
            @RequestBody @Valid RamUpdateRequest request) {
        log.info("Updating RAM with ID: {}", ramId);

        ApiResponse<RamResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ramService.updateRam(request, ramId));

        return apiResponse;
    }

    /**
     * Delete RAM by ID
     * DELETE /rams/{id}
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteRam(@PathVariable("id") String ramId) {
        log.info("Deleting RAM with ID: {}", ramId);

        ramService.deleteRam(ramId);

        return ApiResponse.<Void>builder()
                .message("RAM deleted successfully")
                .build();
    }
}

