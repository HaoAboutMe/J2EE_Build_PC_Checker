package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.SsdCreationRequest;
import com.j2ee.buildpcchecker.dto.request.SsdUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.SsdResponse;
import com.j2ee.buildpcchecker.entity.FormFactor;
import com.j2ee.buildpcchecker.entity.Ssd;
import com.j2ee.buildpcchecker.entity.InterfaceType;
import com.j2ee.buildpcchecker.entity.SsdType;
import com.j2ee.buildpcchecker.exception.AppException;
import com.j2ee.buildpcchecker.exception.ErrorCode;
import com.j2ee.buildpcchecker.mapper.SsdMapper;
import com.j2ee.buildpcchecker.repository.FormFactorRepository;
import com.j2ee.buildpcchecker.repository.InterfaceTypeRepository;
import com.j2ee.buildpcchecker.repository.SsdRepository;
import com.j2ee.buildpcchecker.repository.SsdTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class SsdService {

    SsdRepository ssdRepository;
    SsdMapper ssdMapper;
    FormFactorRepository formFactorRepository;
    SsdTypeRepository ssdTypeRepository;
    InterfaceTypeRepository interfaceTypeRepository;

    /**
     * Create a new SSD
     * @param request SSD creation request
     * @return SsdResponse
     */
    public SsdResponse createSsd(SsdCreationRequest request) {
        log.info("Creating new SSD: {}", request.getName());

        // Check if SSD name already exists
        if (ssdRepository.existsByName(request.getName())) {
            log.error("SSD name already exists: {}", request.getName());
            throw new AppException(ErrorCode.SSD_NAME_ALREADY_EXISTS);
        }

        // Get SsdType
        SsdType ssdType = ssdTypeRepository.findById(request.getSsdTypeId())
                .orElseThrow(() -> {
                    log.error("SSD Type not found with ID: {}", request.getSsdTypeId());
                    return new AppException(ErrorCode.SSD_TYPE_NOT_FOUND);
                });

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

        Ssd ssd = ssdMapper.toSsd(request);
        ssd.setSsdType(ssdType);
        ssd.setFormFactor(formFactor);
        ssd.setInterfaceType(interfaceType);

        Ssd savedSsd = ssdRepository.save(ssd);

        log.info("SSD created successfully with ID: {}", savedSsd.getId());
        return ssdMapper.toSsdResponse(savedSsd);
    }

    /**
     * Get all SSDs
     * @return List of SsdResponse
     */
    public List<SsdResponse> getAllSsds() {
        log.info("Getting all SSDs");
        List<Ssd> ssds = ssdRepository.findAll();
        return ssdMapper.toListSsdResponse(ssds);
    }

    /**
     * Get SSD by ID
     * @param ssdId SSD ID
     * @return SsdResponse
     */
    public SsdResponse getSsdById(String ssdId) {
        log.info("Getting SSD with ID: {}", ssdId);

        Ssd ssd = ssdRepository.findById(ssdId)
                .orElseThrow(() -> {
                    log.error("SSD not found with ID: {}", ssdId);
                    return new AppException(ErrorCode.SSD_NOT_FOUND);
                });

        return ssdMapper.toSsdResponse(ssd);
    }

    /**
     * Update SSD by ID
     * @param request SSD update request
     * @param ssdId SSD ID
     * @return SsdResponse
     */
    public SsdResponse updateSsd(SsdUpdateRequest request, String ssdId) {
        log.info("Updating SSD with ID: {}", ssdId);

        Ssd ssd = ssdRepository.findById(ssdId)
                .orElseThrow(() -> {
                    log.error("SSD not found with ID: {}", ssdId);
                    return new AppException(ErrorCode.SSD_NOT_FOUND);
                });

        ssdMapper.updateSsd(ssd, request);

        // Update SsdType if provided
        if (request.getSsdTypeId() != null) {
            SsdType ssdType = ssdTypeRepository.findById(request.getSsdTypeId())
                    .orElseThrow(() -> {
                        log.error("SSD Type not found with ID: {}", request.getSsdTypeId());
                        return new AppException(ErrorCode.SSD_TYPE_NOT_FOUND);
                    });
            ssd.setSsdType(ssdType);
        }

        // Update FormFactor if provided
        if (request.getFormFactorId() != null) {
            FormFactor formFactor = formFactorRepository.findById(request.getFormFactorId())
                    .orElseThrow(() -> {
                        log.error("Form Factor not found with ID: {}", request.getFormFactorId());
                        return new AppException(ErrorCode.FORM_FACTOR_NOT_FOUND);
                    });
            ssd.setFormFactor(formFactor);
        }

        // Update InterfaceType if provided
        if (request.getInterfaceTypeId() != null) {
            InterfaceType interfaceType = interfaceTypeRepository.findById(request.getInterfaceTypeId())
                    .orElseThrow(() -> {
                        log.error("Interface Type not found with ID: {}", request.getInterfaceTypeId());
                        return new AppException(ErrorCode.INTERFACE_TYPE_NOT_FOUND);
                    });
            ssd.setInterfaceType(interfaceType);
        }

        Ssd updatedSsd = ssdRepository.save(ssd);

        log.info("SSD updated successfully with ID: {}", updatedSsd.getId());
        return ssdMapper.toSsdResponse(updatedSsd);
    }

    /**
     * Delete SSD by ID
     * @param ssdId SSD ID
     */
    public void deleteSsd(String ssdId) {
        log.info("Deleting SSD with ID: {}", ssdId);

        Ssd ssd = ssdRepository.findById(ssdId)
                .orElseThrow(() -> {
                    log.error("SSD not found with ID: {}", ssdId);
                    return new AppException(ErrorCode.SSD_NOT_FOUND);
                });

        ssdRepository.delete(ssd);
        log.info("SSD deleted successfully with ID: {}", ssdId);
    }
}
