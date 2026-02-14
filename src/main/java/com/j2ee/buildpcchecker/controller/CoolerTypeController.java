package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.CoolerTypeRequest;
import com.j2ee.buildpcchecker.dto.response.CoolerTypeResponse;
import com.j2ee.buildpcchecker.service.CoolerTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cooler-types")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CoolerTypeController {

    CoolerTypeService coolerTypeService;

    @PostMapping
    public ApiResponse<CoolerTypeResponse> createCoolerType(@RequestBody @Valid CoolerTypeRequest request) {
        log.info("POST /cooler-types - Creating new Cooler Type");
        return ApiResponse.<CoolerTypeResponse>builder()
                .result(coolerTypeService.createCoolerType(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CoolerTypeResponse>> getAllCoolerTypes() {
        log.info("GET /cooler-types - Getting all Cooler Types");
        return ApiResponse.<List<CoolerTypeResponse>>builder()
                .result(coolerTypeService.getAllCoolerTypes())
                .build();
    }

    @GetMapping("/{coolerTypeId}")
    public ApiResponse<CoolerTypeResponse> getCoolerTypeById(@PathVariable String coolerTypeId) {
        log.info("GET /cooler-types/{} - Getting Cooler Type by ID", coolerTypeId);
        return ApiResponse.<CoolerTypeResponse>builder()
                .result(coolerTypeService.getCoolerTypeById(coolerTypeId))
                .build();
    }

    @PutMapping("/{coolerTypeId}")
    public ApiResponse<CoolerTypeResponse> updateCoolerType(
            @PathVariable String coolerTypeId,
            @RequestBody @Valid CoolerTypeRequest request) {
        log.info("PUT /cooler-types/{} - Updating Cooler Type", coolerTypeId);
        return ApiResponse.<CoolerTypeResponse>builder()
                .result(coolerTypeService.updateCoolerType(request, coolerTypeId))
                .build();
    }

    @DeleteMapping("/{coolerTypeId}")
    public ApiResponse<String> deleteCoolerType(@PathVariable String coolerTypeId) {
        log.info("DELETE /cooler-types/{} - Deleting Cooler Type", coolerTypeId);
        coolerTypeService.deleteCoolerType(coolerTypeId);
        return ApiResponse.<String>builder()
                .result("Cooler Type deleted successfully")
                .build();
    }
}
