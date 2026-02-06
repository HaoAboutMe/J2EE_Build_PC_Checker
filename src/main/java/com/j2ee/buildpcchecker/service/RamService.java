package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.RamCreationRequest;
import com.j2ee.buildpcchecker.dto.request.RamUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.RamResponse;
import com.j2ee.buildpcchecker.entity.Ram;
import com.j2ee.buildpcchecker.entity.RamType;
import com.j2ee.buildpcchecker.mapper.RamMapper;
import com.j2ee.buildpcchecker.repository.RamRepository;
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
public class RamService {

    RamRepository ramRepository;
    RamMapper ramMapper;
    RamTypeRepository ramTypeRepository;

    /**
     * Create a new RAM
     * @param request RAM creation request
     * @return RamResponse
     */
    public RamResponse createRam(RamCreationRequest request) {
        log.info("Creating new RAM: {}", request.getName());

        // Get RamType
        RamType ramType = ramTypeRepository.findById(request.getRamTypeId())
                .orElseThrow(() -> {
                    log.error("RAM Type not found with ID: {}", request.getRamTypeId());
                    return new RuntimeException("RAM Type not found with id: " + request.getRamTypeId());
                });

        Ram ram = ramMapper.toRam(request);
        ram.setRamType(ramType);

        Ram savedRam = ramRepository.save(ram);

        log.info("RAM created successfully with ID: {}", savedRam.getId());
        return ramMapper.toRamResponse(savedRam);
    }

    /**
     * Get all RAMs
     * @return List of RamResponse
     */
    public List<RamResponse> getAllRams() {
        log.info("Getting all RAMs");
        List<Ram> rams = ramRepository.findAll();
        return ramMapper.toListRamResponse(rams);
    }

    /**
     * Get RAM by ID
     * @param ramId RAM ID
     * @return RamResponse
     */
    public RamResponse getRamById(String ramId) {
        log.info("Getting RAM with ID: {}", ramId);

        Ram ram = ramRepository.findById(ramId)
                .orElseThrow(() -> {
                    log.error("RAM not found with ID: {}", ramId);
                    return new RuntimeException("RAM not found with id: " + ramId);
                });

        return ramMapper.toRamResponse(ram);
    }

    /**
     * Update RAM by ID
     * @param request RAM update request
     * @param ramId RAM ID
     * @return RamResponse
     */
    public RamResponse updateRam(RamUpdateRequest request, String ramId) {
        log.info("Updating RAM with ID: {}", ramId);

        Ram ram = ramRepository.findById(ramId)
                .orElseThrow(() -> {
                    log.error("RAM not found with ID: {}", ramId);
                    return new RuntimeException("RAM not found with id: " + ramId);
                });

        ramMapper.updateRam(ram, request);

        // Update RamType if provided
        if (request.getRamTypeId() != null) {
            RamType ramType = ramTypeRepository.findById(request.getRamTypeId())
                    .orElseThrow(() -> {
                        log.error("RAM Type not found with ID: {}", request.getRamTypeId());
                        return new RuntimeException("RAM Type not found with id: " + request.getRamTypeId());
                    });
            ram.setRamType(ramType);
        }

        Ram updatedRam = ramRepository.save(ram);

        log.info("RAM updated successfully with ID: {}", updatedRam.getId());
        return ramMapper.toRamResponse(updatedRam);
    }

    /**
     * Delete RAM by ID
     * @param ramId RAM ID
     */
    public void deleteRam(String ramId) {
        log.info("Deleting RAM with ID: {}", ramId);

        Ram ram = ramRepository.findById(ramId)
                .orElseThrow(() -> {
                    log.error("RAM not found with ID: {}", ramId);
                    return new RuntimeException("RAM not found with id: " + ramId);
                });

        ramRepository.delete(ram);
        log.info("RAM deleted successfully with ID: {}", ramId);
    }
}

