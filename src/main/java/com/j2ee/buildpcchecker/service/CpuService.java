package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.CpuCreationRequest;
import com.j2ee.buildpcchecker.dto.request.CpuUpdateRequest;
import com.j2ee.buildpcchecker.dto.response.CpuResponse;
import com.j2ee.buildpcchecker.entity.Cpu;
import com.j2ee.buildpcchecker.entity.PcieVersion;
import com.j2ee.buildpcchecker.entity.Socket;
import com.j2ee.buildpcchecker.exception.AppException;
import com.j2ee.buildpcchecker.exception.ErrorCode;
import com.j2ee.buildpcchecker.mapper.CpuMapper;
import com.j2ee.buildpcchecker.repository.CpuRepository;
import com.j2ee.buildpcchecker.repository.PcieVersionRepository;
import com.j2ee.buildpcchecker.repository.SocketRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CpuService {

    CpuRepository cpuRepository;
    CpuMapper cpuMapper;
    SocketRepository socketRepository;
    PcieVersionRepository pcieVersionRepository;

    /**
     * Create a new CPU
     * @param request CPU creation request
     * @return CpuResponse
     */
    public CpuResponse createCpu(CpuCreationRequest request) {
        log.info("Creating new CPU: {}", request.getName());

        // Check if CPU name already exists
        if (cpuRepository.existsByName(request.getName())) {
            log.error("CPU name already exists: {}", request.getName());
            throw new AppException(ErrorCode.CPU_NAME_ALREADY_EXISTS);
        }

        // Get Socket
        Socket socket = socketRepository.findById(request.getSocketId())
                .orElseThrow(() -> {
                    log.error("Socket not found with ID: {}", request.getSocketId());
                    return new RuntimeException("Socket not found with id: " + request.getSocketId());
                });

        // Get PcieVersion
        PcieVersion pcieVersion = pcieVersionRepository.findById(request.getPcieVersionId())
                .orElseThrow(() -> {
                    log.error("PCIe Version not found with ID: {}", request.getPcieVersionId());
                    return new RuntimeException("PCIe Version not found with id: " + request.getPcieVersionId());
                });

        Cpu cpu = cpuMapper.toCpu(request);
        cpu.setSocket(socket);
        cpu.setPcieVersion(pcieVersion);

        Cpu savedCpu = cpuRepository.save(cpu);

        log.info("CPU created successfully with ID: {}", savedCpu.getId());
        return cpuMapper.toCpuResponse(savedCpu);
    }

    /**
     * Get all CPUs
     * @return List of CpuResponse
     */
    public List<CpuResponse> getAllCpus() {
        log.info("Getting all CPUs");
        List<Cpu> cpus = cpuRepository.findAll();
        return cpuMapper.toListCpuResponse(cpus);
    }

    /**
     * Get CPU by ID
     * @param cpuId CPU ID
     * @return CpuResponse
     */
    public CpuResponse getCpuById(String cpuId) {
        log.info("Getting CPU with ID: {}", cpuId);

        Cpu cpu = cpuRepository.findById(cpuId)
                .orElseThrow(() -> {
                    log.error("CPU not found with ID: {}", cpuId);
                    return new RuntimeException("CPU not found with id: " + cpuId);
                });

        return cpuMapper.toCpuResponse(cpu);
    }

    /**
     * Update CPU by ID
     * @param request CPU update request
     * @param cpuId CPU ID
     * @return CpuResponse
     */
    public CpuResponse updateCpu(CpuUpdateRequest request, String cpuId) {
        log.info("Updating CPU with ID: {}", cpuId);

        Cpu cpu = cpuRepository.findById(cpuId)
                .orElseThrow(() -> {
                    log.error("CPU not found with ID: {}", cpuId);
                    return new RuntimeException("CPU not found with id: " + cpuId);
                });

        cpuMapper.updateCpu(cpu, request);

        // Update Socket if provided
        if (request.getSocketId() != null) {
            Socket socket = socketRepository.findById(request.getSocketId())
                    .orElseThrow(() -> {
                        log.error("Socket not found with ID: {}", request.getSocketId());
                        return new RuntimeException("Socket not found with id: " + request.getSocketId());
                    });
            cpu.setSocket(socket);
        }

        // Update PcieVersion if provided
        if (request.getPcieVersionId() != null) {
            PcieVersion pcieVersion = pcieVersionRepository.findById(request.getPcieVersionId())
                    .orElseThrow(() -> {
                        log.error("PCIe Version not found with ID: {}", request.getPcieVersionId());
                        return new RuntimeException("PCIe Version not found with id: " + request.getPcieVersionId());
                    });
            cpu.setPcieVersion(pcieVersion);
        }

        Cpu updatedCpu = cpuRepository.save(cpu);

        log.info("CPU updated successfully with ID: {}", updatedCpu.getId());
        return cpuMapper.toCpuResponse(updatedCpu);
    }

    /**
     * Delete CPU by ID
     * @param cpuId CPU ID
     */
    public void deleteCpu(String cpuId) {
        log.info("Deleting CPU with ID: {}", cpuId);

        Cpu cpu = cpuRepository.findById(cpuId)
                .orElseThrow(() -> {
                    log.error("CPU not found with ID: {}", cpuId);
                    return new RuntimeException("CPU not found with id: " + cpuId);
                });

        cpuRepository.delete(cpu);
        log.info("CPU deleted successfully with ID: {}", cpuId);
    }
}

