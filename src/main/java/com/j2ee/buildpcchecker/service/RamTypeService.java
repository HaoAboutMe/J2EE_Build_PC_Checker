package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.RamTypeRequest;
import com.j2ee.buildpcchecker.dto.response.RamTypeResponse;
import com.j2ee.buildpcchecker.entity.RamType;
import com.j2ee.buildpcchecker.mapper.RamTypeMapper;
import com.j2ee.buildpcchecker.repository.RamTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RamTypeService {

    RamTypeRepository ramTypeRepository;
    RamTypeMapper ramTypeMapper;

    /**
     * Create a new RAM Type
     * @param request RAM Type creation request
     * @return RamTypeResponse
     */
    public RamTypeResponse createRamType(RamTypeRequest request) {
        log.info("Creating new RAM Type: {}", request.getId());

        // Check if RAM type already exists
        if (ramTypeRepository.existsById(request.getId())) {
            log.error("RAM Type already exists with ID: {}", request.getId());
            throw new RuntimeException("RAM Type already exists with id: " + request.getId());
        }

        RamType ramType = ramTypeMapper.toRamType(request);
        RamType savedRamType = ramTypeRepository.save(ramType);

        log.info("RAM Type created successfully with ID: {}", savedRamType.getId());
        return ramTypeMapper.toRamTypeResponse(savedRamType);
    }

    /**
     * Get all RAM Types
     * @return List of RamTypeResponse
     */
    public List<RamTypeResponse> getAllRamTypes() {
        log.info("Getting all RAM Types");
        List<RamType> ramTypes = ramTypeRepository.findAll();
        return ramTypeMapper.toListRamTypeResponse(ramTypes);
    }

    /**
     * Get RAM Type by ID
     * @param ramTypeId RAM Type ID
     * @return RamTypeResponse
     */
    public RamTypeResponse getRamTypeById(String ramTypeId) {
        log.info("Getting RAM Type with ID: {}", ramTypeId);

        RamType ramType = ramTypeRepository.findById(ramTypeId)
                .orElseThrow(() -> {
                    log.error("RAM Type not found with ID: {}", ramTypeId);
                    return new RuntimeException("RAM Type not found with id: " + ramTypeId);
                });

        return ramTypeMapper.toRamTypeResponse(ramType);
    }

    /**
     * Update RAM Type by ID
     * @param request RAM Type update request
     * @param ramTypeId RAM Type ID
     * @return RamTypeResponse
     */
    public RamTypeResponse updateRamType(RamTypeRequest request, String ramTypeId) {
        log.info("Updating RAM Type with ID: {}", ramTypeId);

        RamType ramType = ramTypeRepository.findById(ramTypeId)
                .orElseThrow(() -> {
                    log.error("RAM Type not found with ID: {}", ramTypeId);
                    return new RuntimeException("RAM Type not found with id: " + ramTypeId);
                });

        ramTypeMapper.updateRamType(ramType, request);
        RamType updatedRamType = ramTypeRepository.save(ramType);

        log.info("RAM Type updated successfully with ID: {}", updatedRamType.getId());
        return ramTypeMapper.toRamTypeResponse(updatedRamType);
    }

    /**
     * Delete RAM Type by ID
     * @param ramTypeId RAM Type ID
     */
    public void deleteRamType(String ramTypeId) {
        log.info("Deleting RAM Type with ID: {}", ramTypeId);

        RamType ramType = ramTypeRepository.findById(ramTypeId)
                .orElseThrow(() -> {
                    log.error("RAM Type not found with ID: {}", ramTypeId);
                    return new RuntimeException("RAM Type not found with id: " + ramTypeId);
                });

        ramTypeRepository.delete(ramType);
        log.info("RAM Type deleted successfully with ID: {}", ramTypeId);
    }
}

