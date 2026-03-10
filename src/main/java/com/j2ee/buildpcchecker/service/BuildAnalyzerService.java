package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.AnalyzeBuildRequest;
import com.j2ee.buildpcchecker.dto.response.BuildAnalysisResponse;
import com.j2ee.buildpcchecker.entity.Cpu;
import com.j2ee.buildpcchecker.entity.Ram;
import com.j2ee.buildpcchecker.entity.Ssd;
import com.j2ee.buildpcchecker.entity.Vga;
import com.j2ee.buildpcchecker.exception.AppException;
import com.j2ee.buildpcchecker.exception.ErrorCode;
import com.j2ee.buildpcchecker.repository.CpuRepository;
import com.j2ee.buildpcchecker.repository.RamRepository;
import com.j2ee.buildpcchecker.repository.SsdRepository;
import com.j2ee.buildpcchecker.repository.VgaRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service for analyzing PC build bottleneck and power consumption
 */
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class BuildAnalyzerService {

    CpuRepository cpuRepository;
    VgaRepository vgaRepository;
    RamRepository ramRepository;
    SsdRepository ssdRepository;

    /**
     * Analyze PC build for bottleneck and power consumption
     *
     * @param request Build analysis request containing component IDs
     * @return BuildAnalysisResponse with bottleneck percentage, balance status, and estimated wattage
     */
    public BuildAnalysisResponse analyzeBuild(AnalyzeBuildRequest request) {
        log.info("Analyzing build - CPU: {}, GPU: {}, RAM: {}, SSD: {}",
                request.getCpuId(), request.getGpuId(), request.getRamId(), request.getSsdId());

        // Step 1: Fetch all components from repositories
        Cpu cpu = cpuRepository.findById(request.getCpuId())
                .orElseThrow(() -> {
                    log.error("CPU not found with ID: {}", request.getCpuId());
                    return new AppException(ErrorCode.CPU_NOT_FOUND);
                });

        Vga gpu = vgaRepository.findById(request.getGpuId())
                .orElseThrow(() -> {
                    log.error("GPU (VGA) not found with ID: {}", request.getGpuId());
                    return new AppException(ErrorCode.VGA_NOT_FOUND);
                });

        Ram ram = ramRepository.findById(request.getRamId())
                .orElseThrow(() -> {
                    log.error("RAM not found with ID: {}", request.getRamId());
                    return new AppException(ErrorCode.RAM_NOT_FOUND);
                });

        Ssd ssd = ssdRepository.findById(request.getSsdId())
                .orElseThrow(() -> {
                    log.error("SSD not found with ID: {}", request.getSsdId());
                    return new AppException(ErrorCode.SSD_NOT_FOUND);
                });

        // Step 2: Get CPU and GPU benchmark scores
        int cpuScore = cpu.getScore();
        int gpuScore = gpu.getScore();

        log.debug("CPU Score: {}, GPU Score: {}", cpuScore, gpuScore);

        // Step 3: Calculate bottleneck using the formula
        // Formula: ratio = cpuScore / gpuScore
        //          bottleneck = abs(1 - ratio) * 100
        double ratio = (double) cpuScore / gpuScore;
        double bottleneck = Math.abs(1 - ratio) * 100;

        log.debug("Ratio: {}, Bottleneck: {}%", ratio, bottleneck);

        // Step 4: Determine balance status based on bottleneck percentage
        String balanceStatus = determineBalanceStatus(bottleneck);

        // Step 5: Estimate system power usage
        // Formula: cpu.tdp + gpu.tdp + ram.tdp + ssd.tdp + 50W (motherboard and other components)
        int estimatedWattage = cpu.getTdp() + gpu.getTdp() + ram.getTdp() + ssd.getTdp() + 50;

        log.info("Build analysis completed - Bottleneck: {}%, Status: {}, Power: {}W",
                String.format("%.2f", bottleneck), balanceStatus, estimatedWattage);

        return BuildAnalysisResponse.builder()
                .bottleneck(Math.round(bottleneck * 10.0) / 10.0)  // Round to 1 decimal place
                .balanceStatus(balanceStatus)
                .estimatedWattage(estimatedWattage)
                .build();
    }

    /**
     * Determine balance status based on bottleneck percentage
     *
     * @param bottleneck Bottleneck percentage
     * @return Balance status string
     */
    private String determineBalanceStatus(double bottleneck) {
        if (bottleneck <= 10) {
            return "Perfect Balance";
        } else if (bottleneck <= 20) {
            return "Good Balance";
        } else if (bottleneck <= 30) {
            return "Acceptable";
        } else {
            return "High Bottleneck";
        }
    }
}

