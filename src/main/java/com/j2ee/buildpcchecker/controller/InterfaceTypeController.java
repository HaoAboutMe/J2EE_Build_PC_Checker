package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.InterfaceTypeRequest;
import com.j2ee.buildpcchecker.dto.response.InterfaceTypeResponse;
import com.j2ee.buildpcchecker.service.InterfaceTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interface-types")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InterfaceTypeController {

    InterfaceTypeService interfaceTypeService;

    @PostMapping
    public ApiResponse<InterfaceTypeResponse> createInterfaceType(@RequestBody @Valid InterfaceTypeRequest request) {
        log.info("POST /interface-types - Creating new Interface Type");
        return ApiResponse.<InterfaceTypeResponse>builder()
                .result(interfaceTypeService.createInterfaceType(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<InterfaceTypeResponse>> getAllInterfaceTypes() {
        log.info("GET /interface-types - Getting all Interface Types");
        return ApiResponse.<List<InterfaceTypeResponse>>builder()
                .result(interfaceTypeService.getAllInterfaceTypes())
                .build();
    }

    @GetMapping("/{interfaceTypeId}")
    public ApiResponse<InterfaceTypeResponse> getInterfaceTypeById(@PathVariable String interfaceTypeId) {
        log.info("GET /interface-types/{} - Getting Interface Type by ID", interfaceTypeId);
        return ApiResponse.<InterfaceTypeResponse>builder()
                .result(interfaceTypeService.getInterfaceTypeById(interfaceTypeId))
                .build();
    }

    @PutMapping("/{interfaceTypeId}")
    public ApiResponse<InterfaceTypeResponse> updateInterfaceType(
            @PathVariable String interfaceTypeId,
            @RequestBody @Valid InterfaceTypeRequest request) {
        log.info("PUT /interface-types/{} - Updating Interface Type", interfaceTypeId);
        return ApiResponse.<InterfaceTypeResponse>builder()
                .result(interfaceTypeService.updateInterfaceType(request, interfaceTypeId))
                .build();
    }

    @DeleteMapping("/{interfaceTypeId}")
    public ApiResponse<String> deleteInterfaceType(@PathVariable String interfaceTypeId) {
        log.info("DELETE /interface-types/{} - Deleting Interface Type", interfaceTypeId);
        interfaceTypeService.deleteInterfaceType(interfaceTypeId);
        return ApiResponse.<String>builder()
                .result("Interface Type deleted successfully")
                .build();
    }
}

