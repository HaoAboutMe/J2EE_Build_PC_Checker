package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.MainboardCreationRequest;
import com.j2ee.buildpcchecker.dto.request.MainboardUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.MainboardResponse;
import com.j2ee.buildpcchecker.service.MainboardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mainboards")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class MainboardController {

    MainboardService mainboardService;

    /**
     * Create a new Mainboard
     * POST /mainboards
     */
    @PostMapping
    public ApiResponse<MainboardResponse> createMainboard(@RequestBody @Valid MainboardCreationRequest request) {
        log.info("Creating Mainboard: {}", request.getName());

        ApiResponse<MainboardResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(mainboardService.createMainboard(request));

        return apiResponse;
    }

    /**
     * Get all Mainboards
     * GET /mainboards
     */
    @GetMapping
    public ApiResponse<List<MainboardResponse>> getAllMainboards() {
        log.info("Getting all Mainboards");

        ApiResponse<List<MainboardResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(mainboardService.getAllMainboards());

        return apiResponse;
    }

    /**
     * Get Mainboard by ID
     * GET /mainboards/{id}
     */
    @GetMapping("/{id}")
    public ApiResponse<MainboardResponse> getMainboardById(@PathVariable("id") String mainboardId) {
        log.info("Getting Mainboard with ID: {}", mainboardId);

        return ApiResponse.<MainboardResponse>builder()
                .result(mainboardService.getMainboardById(mainboardId))
                .build();
    }

    /**
     * Update Mainboard by ID
     * PUT /mainboards/{id}
     */
    @PutMapping("/{id}")
    public ApiResponse<MainboardResponse> updateMainboard(
            @PathVariable("id") String mainboardId,
            @RequestBody @Valid MainboardUpdateRequest request) {
        log.info("Updating Mainboard with ID: {}", mainboardId);

        ApiResponse<MainboardResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(mainboardService.updateMainboard(request, mainboardId));

        return apiResponse;
    }

    /**
     * Delete Mainboard by ID
     * DELETE /mainboards/{id}
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteMainboard(@PathVariable("id") String mainboardId) {
        log.info("Deleting Mainboard with ID: {}", mainboardId);

        mainboardService.deleteMainboard(mainboardId);

        return ApiResponse.<Void>builder()
                .message("Mainboard deleted successfully")
                .build();
    }
}

