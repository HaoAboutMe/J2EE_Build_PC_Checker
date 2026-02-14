package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.CaseCreationRequest;
import com.j2ee.buildpcchecker.dto.request.CaseUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.CaseResponse;
import com.j2ee.buildpcchecker.service.CaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cases")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CaseController {

    CaseService caseService;

    /**
     * Create a new Case
     * POST /cases
     */
    @PostMapping
    public ApiResponse<CaseResponse> createCase(@RequestBody @Valid CaseCreationRequest request) {
        log.info("Creating Case: {}", request.getName());

        ApiResponse<CaseResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(caseService.createCase(request));

        return apiResponse;
    }

    /**
     * Get all Cases
     * GET /cases
     */
    @GetMapping
    public ApiResponse<List<CaseResponse>> getAllCases() {
        log.info("Getting all Cases");

        ApiResponse<List<CaseResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(caseService.getAllCases());

        return apiResponse;
    }

    /**
     * Get Case by ID
     * GET /cases/{id}
     */
    @GetMapping("/{id}")
    public ApiResponse<CaseResponse> getCaseById(@PathVariable("id") String caseId) {
        log.info("Getting Case with ID: {}", caseId);

        return ApiResponse.<CaseResponse>builder()
                .result(caseService.getCaseById(caseId))
                .build();
    }

    /**
     * Update Case by ID
     * PUT /cases/{id}
     */
    @PutMapping("/{id}")
    public ApiResponse<CaseResponse> updateCase(
            @PathVariable("id") String caseId,
            @RequestBody @Valid CaseUpdateRequest request) {
        log.info("Updating Case with ID: {}", caseId);

        ApiResponse<CaseResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(caseService.updateCase(request, caseId));

        return apiResponse;
    }

    /**
     * Delete Case by ID
     * DELETE /cases/{id}
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCase(@PathVariable("id") String caseId) {
        log.info("Deleting Case with ID: {}", caseId);

        caseService.deleteCase(caseId);

        return ApiResponse.<Void>builder()
                .message("Case deleted successfully")
                .build();
    }
}
