package com.j2ee.buildpcchecker.controller;

import com.j2ee.buildpcchecker.dto.request.ApiResponse;
import com.j2ee.buildpcchecker.dto.request.FormFactorRequest;
import com.j2ee.buildpcchecker.dto.response.FormFactorResponse;
import com.j2ee.buildpcchecker.service.FormFactorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/form-factors")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class FormFactorController {

    FormFactorService formFactorService;

    @PostMapping
    public ApiResponse<FormFactorResponse> createFormFactor(@RequestBody @Valid FormFactorRequest request) {
        log.info("Creating Form Factor: {}", request.getId());

        ApiResponse<FormFactorResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(formFactorService.createFormFactor(request));

        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<FormFactorResponse>> getAllFormFactors() {
        log.info("Getting all Form Factors");

        ApiResponse<List<FormFactorResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(formFactorService.getAllFormFactors());

        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<FormFactorResponse> getFormFactorById(@PathVariable("id") String formFactorId) {
        log.info("Getting Form Factor with ID: {}", formFactorId);

        return ApiResponse.<FormFactorResponse>builder()
                .result(formFactorService.getFormFactorById(formFactorId))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<FormFactorResponse> updateFormFactor(
            @PathVariable("id") String formFactorId,
            @RequestBody @Valid FormFactorRequest request) {
        log.info("Updating Form Factor with ID: {}", formFactorId);

        ApiResponse<FormFactorResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(formFactorService.updateFormFactor(request, formFactorId));

        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteFormFactor(@PathVariable("id") String formFactorId) {
        log.info("Deleting Form Factor with ID: {}", formFactorId);

        formFactorService.deleteFormFactor(formFactorId);

        return ApiResponse.<Void>builder()
                .message("Form Factor deleted successfully")
                .build();
    }
}

