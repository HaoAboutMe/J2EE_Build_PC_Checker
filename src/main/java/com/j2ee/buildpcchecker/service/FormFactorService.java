package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.FormFactorRequest;
import com.j2ee.buildpcchecker.dto.response.FormFactorResponse;
import com.j2ee.buildpcchecker.entity.FormFactor;
import com.j2ee.buildpcchecker.exception.AppException;
import com.j2ee.buildpcchecker.exception.ErrorCode;
import com.j2ee.buildpcchecker.mapper.FormFactorMapper;
import com.j2ee.buildpcchecker.repository.FormFactorRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class FormFactorService {

    FormFactorRepository formFactorRepository;
    FormFactorMapper formFactorMapper;

    public FormFactorResponse createFormFactor(FormFactorRequest request) {
        log.info("Creating new Form Factor: {}", request.getId());

        if (formFactorRepository.existsById(request.getId())) {
            throw new AppException(ErrorCode.FORM_FACTOR_ALREADY_EXISTS);
        }

        FormFactor formFactor = formFactorMapper.toFormFactor(request);
        FormFactor savedFormFactor = formFactorRepository.save(formFactor);

        log.info("Form Factor created successfully with ID: {}", savedFormFactor.getId());
        return formFactorMapper.toFormFactorResponse(savedFormFactor);
    }

    public List<FormFactorResponse> getAllFormFactors() {
        log.info("Getting all Form Factors");
        List<FormFactor> formFactors = formFactorRepository.findAll();
        return formFactorMapper.toListFormFactorResponse(formFactors);
    }

    public FormFactorResponse getFormFactorById(String formFactorId) {
        log.info("Getting Form Factor with ID: {}", formFactorId);

        FormFactor formFactor = formFactorRepository.findById(formFactorId)
                .orElseThrow(() -> {
                    log.error("Form Factor not found with ID: {}", formFactorId);
                    return new AppException(ErrorCode.FORM_FACTOR_NOT_FOUND);
                });

        return formFactorMapper.toFormFactorResponse(formFactor);
    }

    public FormFactorResponse updateFormFactor(FormFactorRequest request, String formFactorId) {
        log.info("Updating Form Factor with ID: {}", formFactorId);

        FormFactor formFactor = formFactorRepository.findById(formFactorId)
                .orElseThrow(() -> {
                    log.error("Form Factor not found with ID: {}", formFactorId);
                    return new AppException(ErrorCode.FORM_FACTOR_NOT_FOUND);
                });

        formFactorMapper.updateFormFactor(formFactor, request);
        FormFactor updatedFormFactor = formFactorRepository.save(formFactor);

        log.info("Form Factor updated successfully with ID: {}", updatedFormFactor.getId());
        return formFactorMapper.toFormFactorResponse(updatedFormFactor);
    }

    public void deleteFormFactor(String formFactorId) {
        log.info("Deleting Form Factor with ID: {}", formFactorId);

        FormFactor formFactor = formFactorRepository.findById(formFactorId)
                .orElseThrow(() -> {
                    log.error("Form Factor not found with ID: {}", formFactorId);
                    return new AppException(ErrorCode.FORM_FACTOR_NOT_FOUND);
                });

        formFactorRepository.delete(formFactor);
        log.info("Form Factor deleted successfully with ID: {}", formFactorId);
    }
}

