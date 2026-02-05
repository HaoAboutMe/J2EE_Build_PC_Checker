package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.PcieVersionRequest;
import com.j2ee.buildpcchecker.dto.response.PcieVersionResponse;
import com.j2ee.buildpcchecker.entity.PcieVersion;
import com.j2ee.buildpcchecker.mapper.PcieVersionMapper;
import com.j2ee.buildpcchecker.repository.PcieVersionRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class PcieVersionService {

    PcieVersionRepository pcieVersionRepository;
    PcieVersionMapper pcieVersionMapper;

    /**
     * Create a new PCIe Version
     * @param request PCIe Version creation request
     * @return PcieVersionResponse
     */
    public PcieVersionResponse createPcieVersion(PcieVersionRequest request) {
        log.info("Creating new PCIe Version: {}", request.getId());

        // Check if PCIe version already exists
        if (pcieVersionRepository.existsById(request.getId())) {
            log.error("PCIe Version already exists with ID: {}", request.getId());
            throw new RuntimeException("PCIe Version already exists with id: " + request.getId());
        }

        PcieVersion pcieVersion = pcieVersionMapper.toPcieVersion(request);
        PcieVersion savedPcieVersion = pcieVersionRepository.save(pcieVersion);

        log.info("PCIe Version created successfully with ID: {}", savedPcieVersion.getId());
        return pcieVersionMapper.toPcieVersionResponse(savedPcieVersion);
    }

    /**
     * Get all PCIe Versions
     * @return List of PcieVersionResponse
     */
    public List<PcieVersionResponse> getAllPcieVersions() {
        log.info("Getting all PCIe Versions");
        List<PcieVersion> pcieVersions = pcieVersionRepository.findAll();
        return pcieVersionMapper.toListPcieVersionResponse(pcieVersions);
    }

    /**
     * Get PCIe Version by ID
     * @param pcieVersionId PCIe Version ID
     * @return PcieVersionResponse
     */
    public PcieVersionResponse getPcieVersionById(String pcieVersionId) {
        log.info("Getting PCIe Version with ID: {}", pcieVersionId);

        PcieVersion pcieVersion = pcieVersionRepository.findById(pcieVersionId)
                .orElseThrow(() -> {
                    log.error("PCIe Version not found with ID: {}", pcieVersionId);
                    return new RuntimeException("PCIe Version not found with id: " + pcieVersionId);
                });

        return pcieVersionMapper.toPcieVersionResponse(pcieVersion);
    }

    /**
     * Update PCIe Version by ID
     * @param request PCIe Version update request
     * @param pcieVersionId PCIe Version ID
     * @return PcieVersionResponse
     */
    public PcieVersionResponse updatePcieVersion(PcieVersionRequest request, String pcieVersionId) {
        log.info("Updating PCIe Version with ID: {}", pcieVersionId);

        PcieVersion pcieVersion = pcieVersionRepository.findById(pcieVersionId)
                .orElseThrow(() -> {
                    log.error("PCIe Version not found with ID: {}", pcieVersionId);
                    return new RuntimeException("PCIe Version not found with id: " + pcieVersionId);
                });

        pcieVersionMapper.updatePcieVersion(pcieVersion, request);
        PcieVersion updatedPcieVersion = pcieVersionRepository.save(pcieVersion);

        log.info("PCIe Version updated successfully with ID: {}", updatedPcieVersion.getId());
        return pcieVersionMapper.toPcieVersionResponse(updatedPcieVersion);
    }

    /**
     * Delete PCIe Version by ID
     * @param pcieVersionId PCIe Version ID
     */
    public void deletePcieVersion(String pcieVersionId) {
        log.info("Deleting PCIe Version with ID: {}", pcieVersionId);

        PcieVersion pcieVersion = pcieVersionRepository.findById(pcieVersionId)
                .orElseThrow(() -> {
                    log.error("PCIe Version not found with ID: {}", pcieVersionId);
                    return new RuntimeException("PCIe Version not found with id: " + pcieVersionId);
                });

        pcieVersionRepository.delete(pcieVersion);
        log.info("PCIe Version deleted successfully with ID: {}", pcieVersionId);
    }
}

