package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.RamTypeRequest;
import com.j2ee.buildpcchecker.dto.response.RamTypeResponse;
import com.j2ee.buildpcchecker.service.RamTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ram-types")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RamTypeController {

    RamTypeService ramTypeService;

    /**
     * Create a new RAM Type
     * POST /ram-types
     */
    @PostMapping
    public ApiResponse<RamTypeResponse> createRamType(@RequestBody @Valid RamTypeRequest request) {
        log.info("Creating RAM Type: {}", request.getId());

        ApiResponse<RamTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ramTypeService.createRamType(request));

        return apiResponse;
    }

    /**
     * Get all RAM Types
     * GET /ram-types
     */
    @GetMapping
    public ApiResponse<List<RamTypeResponse>> getAllRamTypes() {
        log.info("Getting all RAM Types");

        ApiResponse<List<RamTypeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ramTypeService.getAllRamTypes());

        return apiResponse;
    }

    /**
     * Get RAM Type by ID
     * GET /ram-types/{id}
     */
    @GetMapping("/{id}")
    public ApiResponse<RamTypeResponse> getRamTypeById(@PathVariable("id") String ramTypeId) {
        log.info("Getting RAM Type with ID: {}", ramTypeId);

        return ApiResponse.<RamTypeResponse>builder()
                .result(ramTypeService.getRamTypeById(ramTypeId))
                .build();
    }

    /**
     * Update RAM Type by ID
     * PUT /ram-types/{id}
     */
    @PutMapping("/{id}")
    public ApiResponse<RamTypeResponse> updateRamType(
            @PathVariable("id") String ramTypeId,
            @RequestBody @Valid RamTypeRequest request) {
        log.info("Updating RAM Type with ID: {}", ramTypeId);

        ApiResponse<RamTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ramTypeService.updateRamType(request, ramTypeId));

        return apiResponse;
    }

    /**
     * Delete RAM Type by ID
     * DELETE /ram-types/{id}
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteRamType(@PathVariable("id") String ramTypeId) {
        log.info("Deleting RAM Type with ID: {}", ramTypeId);

        ramTypeService.deleteRamType(ramTypeId);

        return ApiResponse.<Void>builder()
                .message("RAM Type deleted successfully")
                .build();
    }
}

