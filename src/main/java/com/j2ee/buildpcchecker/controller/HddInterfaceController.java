package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.HddInterfaceRequest;
import com.j2ee.buildpcchecker.dto.response.HddInterfaceResponse;
import com.j2ee.buildpcchecker.service.HddInterfaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hdd-interfaces")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class HddInterfaceController {

    HddInterfaceService hddInterfaceService;

    @PostMapping
    public ApiResponse<HddInterfaceResponse> createHddInterface(@RequestBody @Valid HddInterfaceRequest request) {
        log.info("POST /hdd-interfaces - Creating new HDD Interface");
        return ApiResponse.<HddInterfaceResponse>builder()
                .result(hddInterfaceService.createHddInterface(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<HddInterfaceResponse>> getAllHddInterfaces() {
        log.info("GET /hdd-interfaces - Getting all HDD Interfaces");
        return ApiResponse.<List<HddInterfaceResponse>>builder()
                .result(hddInterfaceService.getAllHddInterfaces())
                .build();
    }

    @GetMapping("/{hddInterfaceId}")
    public ApiResponse<HddInterfaceResponse> getHddInterfaceById(@PathVariable String hddInterfaceId) {
        log.info("GET /hdd-interfaces/{} - Getting HDD Interface by ID", hddInterfaceId);
        return ApiResponse.<HddInterfaceResponse>builder()
                .result(hddInterfaceService.getHddInterfaceById(hddInterfaceId))
                .build();
    }

    @PutMapping("/{hddInterfaceId}")
    public ApiResponse<HddInterfaceResponse> updateHddInterface(
            @PathVariable String hddInterfaceId,
            @RequestBody @Valid HddInterfaceRequest request) {
        log.info("PUT /hdd-interfaces/{} - Updating HDD Interface", hddInterfaceId);
        return ApiResponse.<HddInterfaceResponse>builder()
                .result(hddInterfaceService.updateHddInterface(request, hddInterfaceId))
                .build();
    }

    @DeleteMapping("/{hddInterfaceId}")
    public ApiResponse<String> deleteHddInterface(@PathVariable String hddInterfaceId) {
        log.info("DELETE /hdd-interfaces/{} - Deleting HDD Interface", hddInterfaceId);
        hddInterfaceService.deleteHddInterface(hddInterfaceId);
        return ApiResponse.<String>builder()
                .result("HDD Interface deleted successfully")
                .build();
    }
}
