package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.HddCreationRequest;
import com.j2ee.buildpcchecker.dto.request.HddUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.HddResponse;
import com.j2ee.buildpcchecker.entity.FormFactor;
import com.j2ee.buildpcchecker.entity.Hdd;
import com.j2ee.buildpcchecker.entity.InterfaceType;
import com.j2ee.buildpcchecker.exception.AppException;
import com.j2ee.buildpcchecker.exception.ErrorCode;
import com.j2ee.buildpcchecker.mapper.HddMapper;
import com.j2ee.buildpcchecker.repository.FormFactorRepository;
import com.j2ee.buildpcchecker.repository.InterfaceTypeRepository;
import com.j2ee.buildpcchecker.repository.HddRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class HddService {

    HddRepository hddRepository;
    HddMapper hddMapper;
    InterfaceTypeRepository interfaceTypeRepository;
    FormFactorRepository formFactorRepository;

    /**
     * Create a new HDD
     * @param request HDD creation request
     * @return HddResponse
     */
    public HddResponse createHdd(HddCreationRequest request) {
        log.info("Creating new HDD: {}", request.getName());

        // Check if HDD name already exists
        if (hddRepository.existsByName(request.getName())) {
            log.error("HDD name already exists: {}", request.getName());
            throw new AppException(ErrorCode.HDD_NAME_ALREADY_EXISTS);
        }

        // Get FormFactor
        FormFactor formFactor = formFactorRepository.findById(request.getFormFactorId())
                .orElseThrow(() -> {
                    log.error("Form Factor not found with ID: {}", request.getFormFactorId());
                    return new AppException(ErrorCode.FORM_FACTOR_NOT_FOUND);
                });

        // Get InterfaceType
        InterfaceType interfaceType = interfaceTypeRepository.findById(request.getInterfaceTypeId())
                .orElseThrow(() -> {
                    log.error("Interface Type not found with ID: {}", request.getInterfaceTypeId());
                    return new AppException(ErrorCode.INTERFACE_TYPE_NOT_FOUND);
                });

        Hdd hdd = hddMapper.toHdd(request);
        hdd.setFormFactor(formFactor);
        hdd.setInterfaceType(interfaceType);

        Hdd savedHdd = hddRepository.save(hdd);

        log.info("HDD created successfully with ID: {}", savedHdd.getId());
        return hddMapper.toHddResponse(savedHdd);
    }

    /**
     * Get all HDDs
     * @return List of HddResponse
     */
    public List<HddResponse> getAllHdds() {
        log.info("Getting all HDDs");
        List<Hdd> hdds = hddRepository.findAll();
        return hddMapper.toListHddResponse(hdds);
    }

    /**
     * Get HDD by ID
     * @param hddId HDD ID
     * @return HddResponse
     */
    public HddResponse getHddById(String hddId) {
        log.info("Getting HDD with ID: {}", hddId);

        Hdd hdd = hddRepository.findById(hddId)
                .orElseThrow(() -> {
                    log.error("HDD not found with ID: {}", hddId);
                    return new AppException(ErrorCode.HDD_NOT_FOUND);
                });

        return hddMapper.toHddResponse(hdd);
    }

    /**
     * Update HDD by ID
     * @param request HDD update request
     * @param hddId HDD ID
     * @return HddResponse
     */
    public HddResponse updateHdd(HddUpdateRequest request, String hddId) {
        log.info("Updating HDD with ID: {}", hddId);

        Hdd hdd = hddRepository.findById(hddId)
                .orElseThrow(() -> {
                    log.error("HDD not found with ID: {}", hddId);
                    return new AppException(ErrorCode.HDD_NOT_FOUND);
                });

        hddMapper.updateHdd(hdd, request);

        // Update FormFactor if provided
        if (request.getFormFactorId() != null) {
            FormFactor formFactor = formFactorRepository.findById(request.getFormFactorId())
                    .orElseThrow(() -> {
                        log.error("Form Factor not found with ID: {}", request.getFormFactorId());
                        return new AppException(ErrorCode.FORM_FACTOR_NOT_FOUND);
                    });
            hdd.setFormFactor(formFactor);
        }

        // Update InterfaceType if provided
        if (request.getInterfaceTypeId() != null) {
            InterfaceType interfaceType = interfaceTypeRepository.findById(request.getInterfaceTypeId())
                    .orElseThrow(() -> {
                        log.error("Interface Type not found with ID: {}", request.getInterfaceTypeId());
                        return new AppException(ErrorCode.INTERFACE_TYPE_NOT_FOUND);
                    });
            hdd.setInterfaceType(interfaceType);
        }

        Hdd updatedHdd = hddRepository.save(hdd);

        log.info("HDD updated successfully with ID: {}", updatedHdd.getId());
        return hddMapper.toHddResponse(updatedHdd);
    }

    /**
     * Delete HDD by ID
     * @param hddId HDD ID
     */
    public void deleteHdd(String hddId) {
        log.info("Deleting HDD with ID: {}", hddId);

        Hdd hdd = hddRepository.findById(hddId)
                .orElseThrow(() -> {
                    log.error("HDD not found with ID: {}", hddId);
                    return new AppException(ErrorCode.HDD_NOT_FOUND);
                });

        hddRepository.delete(hdd);
        log.info("HDD deleted successfully with ID: {}", hddId);
    }
}
