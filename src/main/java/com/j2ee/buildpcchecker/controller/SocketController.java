package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.SocketRequest;
import com.j2ee.buildpcchecker.dto.response.SocketResponse;
import com.j2ee.buildpcchecker.service.SocketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sockets")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class SocketController {

    SocketService socketService;

    /**
     * Create a new Socket
     * POST /sockets
     */
    @PostMapping
    public ApiResponse<SocketResponse> createSocket(@RequestBody @Valid SocketRequest request) {
        log.info("Creating Socket: {}", request.getId());

        ApiResponse<SocketResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(socketService.createSocket(request));

        return apiResponse;
    }

    /**
     * Get all Sockets
     * GET /sockets
     */
    @GetMapping
    public ApiResponse<List<SocketResponse>> getAllSockets() {
        log.info("Getting all Sockets");

        ApiResponse<List<SocketResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(socketService.getAllSockets());

        return apiResponse;
    }

    /**
     * Get Socket by ID
     * GET /sockets/{id}
     */
    @GetMapping("/{id}")
    public ApiResponse<SocketResponse> getSocketById(@PathVariable("id") String socketId) {
        log.info("Getting Socket with ID: {}", socketId);

        return ApiResponse.<SocketResponse>builder()
                .result(socketService.getSocketById(socketId))
                .build();
    }

    /**
     * Update Socket by ID
     * PUT /sockets/{id}
     */
    @PutMapping("/{id}")
    public ApiResponse<SocketResponse> updateSocket(
            @PathVariable("id") String socketId,
            @RequestBody @Valid SocketRequest request) {
        log.info("Updating Socket with ID: {}", socketId);

        ApiResponse<SocketResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(socketService.updateSocket(request, socketId));

        return apiResponse;
    }

    /**
     * Delete Socket by ID
     * DELETE /sockets/{id}
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteSocket(@PathVariable("id") String socketId) {
        log.info("Deleting Socket with ID: {}", socketId);

        socketService.deleteSocket(socketId);

        return ApiResponse.<Void>builder()
                .message("Socket deleted successfully")
                .build();
    }
}

