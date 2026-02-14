package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.CoolerCreationRequest;
import com.j2ee.buildpcchecker.dto.request.CoolerUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.CoolerResponse;
import com.j2ee.buildpcchecker.entity.Cooler;
import com.j2ee.buildpcchecker.entity.CoolerType;
import com.j2ee.buildpcchecker.exception.AppException;
import com.j2ee.buildpcchecker.exception.ErrorCode;
import com.j2ee.buildpcchecker.mapper.CoolerMapper;
import com.j2ee.buildpcchecker.repository.CoolerRepository;
import com.j2ee.buildpcchecker.repository.CoolerTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CoolerService {

    CoolerRepository coolerRepository;
    CoolerTypeRepository coolerTypeRepository;
    CoolerMapper coolerMapper;

    /**
     * Create a new Cooler
     * @param request Cooler creation request
     * @return CoolerResponse
     */
    public CoolerResponse createCooler(CoolerCreationRequest request) {
        log.info("Creating new Cooler: {}", request.getName());

        // Check if Cooler name already exists
        if (coolerRepository.existsByName(request.getName())) {
            log.error("Cooler name already exists: {}", request.getName());
            throw new AppException(ErrorCode.COOLER_NAME_ALREADY_EXISTS);
        }

        Cooler cooler = coolerMapper.toCooler(request);
        
        // Fetch and set CoolerType
        CoolerType coolerType = coolerTypeRepository.findById(request.getCoolerTypeId())
                .orElseThrow(() -> {
                    log.error("CoolerType not found with ID: {}", request.getCoolerTypeId());
                    return new AppException(ErrorCode.COOLER_TYPE_NOT_FOUND);
                });
        cooler.setCoolerType(coolerType);
        
        Cooler savedCooler = coolerRepository.save(cooler);

        log.info("Cooler created successfully with ID: {}", savedCooler.getId());
        return coolerMapper.toCoolerResponse(savedCooler);
    }

    /**
     * Get all Coolers
     * @return List of CoolerResponse
     */
    public List<CoolerResponse> getAllCoolers() {
        log.info("Getting all Coolers");
        List<Cooler> coolers = coolerRepository.findAll();
        return coolerMapper.toListCoolerResponse(coolers);
    }

    /**
     * Get Cooler by ID
     * @param coolerId Cooler ID
     * @return CoolerResponse
     */
    public CoolerResponse getCoolerById(String coolerId) {
        log.info("Getting Cooler with ID: {}", coolerId);

        Cooler cooler = coolerRepository.findById(coolerId)
                .orElseThrow(() -> {
                    log.error("Cooler not found with ID: {}", coolerId);
                    return new AppException(ErrorCode.COOLER_NOT_FOUND);
                });

        return coolerMapper.toCoolerResponse(cooler);
    }

    /**
     * Update Cooler by ID
     * @param request Cooler update request
     * @param coolerId Cooler ID
     * @return CoolerResponse
     */
    public CoolerResponse updateCooler(CoolerUpdateRequest request, String coolerId) {
        log.info("Updating Cooler with ID: {}", coolerId);

        Cooler cooler = coolerRepository.findById(coolerId)
                .orElseThrow(() -> {
                    log.error("Cooler not found with ID: {}", coolerId);
                    return new AppException(ErrorCode.COOLER_NOT_FOUND);
                });

        coolerMapper.updateCooler(cooler, request);
        
        // Update CoolerType if provided
        if (request.getCoolerTypeId() != null) {
            CoolerType coolerType = coolerTypeRepository.findById(request.getCoolerTypeId())
                    .orElseThrow(() -> {
                        log.error("CoolerType not found with ID: {}", request.getCoolerTypeId());
                        return new AppException(ErrorCode.COOLER_TYPE_NOT_FOUND);
                    });
            cooler.setCoolerType(coolerType);
        }
        
        Cooler updatedCooler = coolerRepository.save(cooler);

        log.info("Cooler updated successfully with ID: {}", updatedCooler.getId());
        return coolerMapper.toCoolerResponse(updatedCooler);
    }

    /**
     * Delete Cooler by ID
     * @param coolerId Cooler ID
     */
    public void deleteCooler(String coolerId) {
        log.info("Deleting Cooler with ID: {}", coolerId);

        Cooler cooler = coolerRepository.findById(coolerId)
                .orElseThrow(() -> {
                    log.error("Cooler not found with ID: {}", coolerId);
                    return new AppException(ErrorCode.COOLER_NOT_FOUND);
                });

        coolerRepository.delete(cooler);
        log.info("Cooler deleted successfully with ID: {}", coolerId);
    }
}
