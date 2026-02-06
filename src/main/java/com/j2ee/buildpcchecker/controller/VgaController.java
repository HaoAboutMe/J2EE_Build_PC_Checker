package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.VgaCreationRequest;
import com.j2ee.buildpcchecker.dto.request.VgaUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.VgaResponse;
import com.j2ee.buildpcchecker.service.VgaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/vgas")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class VgaController {

    VgaService vgaService;

    /**
     * Create a new VGA
     * POST /vgas
     */
    @PostMapping
    public ApiResponse<VgaResponse> createVga(@RequestBody @Valid VgaCreationRequest request) {
        log.info("Creating VGA: {}", request.getName());

        ApiResponse<VgaResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(vgaService.createVga(request));

        return apiResponse;
    }

    /**
     * Get all VGAs
     * GET /vgas
     */
    @GetMapping
    public ApiResponse<List<VgaResponse>> getAllVgas() {
        log.info("Getting all VGAs");

        ApiResponse<List<VgaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(vgaService.getAllVgas());

        return apiResponse;
    }

    /**
     * Get VGA by ID
     * GET /vgas/{id}
     */
    @GetMapping("/{id}")
    public ApiResponse<VgaResponse> getVgaById(@PathVariable("id") String vgaId) {
        log.info("Getting VGA with ID: {}", vgaId);

        return ApiResponse.<VgaResponse>builder()
                .result(vgaService.getVgaById(vgaId))
                .build();
    }

    /**
     * Update VGA by ID
     * PUT /vgas/{id}
     */
    @PutMapping("/{id}")
    public ApiResponse<VgaResponse> updateVga(
            @PathVariable("id") String vgaId,
            @RequestBody @Valid VgaUpdateRequest request) {
        log.info("Updating VGA with ID: {}", vgaId);

        ApiResponse<VgaResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(vgaService.updateVga(request, vgaId));

        return apiResponse;
    }

    /**
     * Delete VGA by ID
     * DELETE /vgas/{id}
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteVga(@PathVariable("id") String vgaId) {
        log.info("Deleting VGA with ID: {}", vgaId);

        vgaService.deleteVga(vgaId);

        return ApiResponse.<Void>builder()
                .message("VGA deleted successfully")
                .build();
    }
}

