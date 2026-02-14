package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.SsdTypeRequest;
import com.j2ee.buildpcchecker.dto.response.SsdTypeResponse;
import com.j2ee.buildpcchecker.service.SsdTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ssd-types")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class SsdTypeController {

    SsdTypeService ssdTypeService;

    @PostMapping
    public ApiResponse<SsdTypeResponse> createSsdType(@RequestBody @Valid SsdTypeRequest request) {
        log.info("Creating SSD Type: {}", request.getId());

        ApiResponse<SsdTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ssdTypeService.createSsdType(request));

        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<SsdTypeResponse>> getAllSsdTypes() {
        log.info("Getting all SSD Types");

        ApiResponse<List<SsdTypeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ssdTypeService.getAllSsdTypes());

        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<SsdTypeResponse> getSsdTypeById(@PathVariable("id") String ssdTypeId) {
        log.info("Getting SSD Type with ID: {}", ssdTypeId);

        return ApiResponse.<SsdTypeResponse>builder()
                .result(ssdTypeService.getSsdTypeById(ssdTypeId))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<SsdTypeResponse> updateSsdType(
            @PathVariable("id") String ssdTypeId,
            @RequestBody @Valid SsdTypeRequest request) {
        log.info("Updating SSD Type with ID: {}", ssdTypeId);

        ApiResponse<SsdTypeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ssdTypeService.updateSsdType(request, ssdTypeId));

        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteSsdType(@PathVariable("id") String ssdTypeId) {
        log.info("Deleting SSD Type with ID: {}", ssdTypeId);

        ssdTypeService.deleteSsdType(ssdTypeId);

        return ApiResponse.<Void>builder()
                .message("SSD Type deleted successfully")
                .build();
    }
}
