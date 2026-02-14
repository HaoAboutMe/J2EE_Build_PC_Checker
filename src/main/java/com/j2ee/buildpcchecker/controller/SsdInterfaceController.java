package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.SsdInterfaceRequest;
import com.j2ee.buildpcchecker.dto.response.SsdInterfaceResponse;
import com.j2ee.buildpcchecker.service.SsdInterfaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ssd-interfaces")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class SsdInterfaceController {

    SsdInterfaceService ssdInterfaceService;

    @PostMapping
    public ApiResponse<SsdInterfaceResponse> createSsdInterface(@RequestBody @Valid SsdInterfaceRequest request) {
        log.info("Creating SSD Interface: {}", request.getId());

        ApiResponse<SsdInterfaceResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ssdInterfaceService.createSsdInterface(request));

        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<SsdInterfaceResponse>> getAllSsdInterfaces() {
        log.info("Getting all SSD Interfaces");

        ApiResponse<List<SsdInterfaceResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ssdInterfaceService.getAllSsdInterfaces());

        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<SsdInterfaceResponse> getSsdInterfaceById(@PathVariable("id") String ssdInterfaceId) {
        log.info("Getting SSD Interface with ID: {}", ssdInterfaceId);

        return ApiResponse.<SsdInterfaceResponse>builder()
                .result(ssdInterfaceService.getSsdInterfaceById(ssdInterfaceId))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<SsdInterfaceResponse> updateSsdInterface(
            @PathVariable("id") String ssdInterfaceId,
            @RequestBody @Valid SsdInterfaceRequest request) {
        log.info("Updating SSD Interface with ID: {}", ssdInterfaceId);

        ApiResponse<SsdInterfaceResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ssdInterfaceService.updateSsdInterface(request, ssdInterfaceId));

        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteSsdInterface(@PathVariable("id") String ssdInterfaceId) {
        log.info("Deleting SSD Interface with ID: {}", ssdInterfaceId);

        ssdInterfaceService.deleteSsdInterface(ssdInterfaceId);

        return ApiResponse.<Void>builder()
                .message("SSD Interface deleted successfully")
                .build();
    }
}
