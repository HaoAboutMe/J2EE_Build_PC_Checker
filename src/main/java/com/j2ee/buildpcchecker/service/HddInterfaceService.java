package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.HddInterfaceRequest;
import com.j2ee.buildpcchecker.dto.response.HddInterfaceResponse;
import com.j2ee.buildpcchecker.entity.HddInterface;
import com.j2ee.buildpcchecker.mapper.HddInterfaceMapper;
import com.j2ee.buildpcchecker.repository.HddInterfaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class HddInterfaceService {

    HddInterfaceRepository hddInterfaceRepository;
    HddInterfaceMapper hddInterfaceMapper;

    public HddInterfaceResponse createHddInterface(HddInterfaceRequest request) {
        log.info("Creating new HDD Interface: {}", request.getId());

        if (hddInterfaceRepository.existsById(request.getId())) {
            throw new RuntimeException("HDD Interface already exists with id: " + request.getId());
        }

        HddInterface hddInterface = hddInterfaceMapper.toHddInterface(request);
        HddInterface savedHddInterface = hddInterfaceRepository.save(hddInterface);

        log.info("HDD Interface created successfully with ID: {}", savedHddInterface.getId());
        return hddInterfaceMapper.toHddInterfaceResponse(savedHddInterface);
    }

    public List<HddInterfaceResponse> getAllHddInterfaces() {
        log.info("Getting all HDD Interfaces");
        List<HddInterface> hddInterfaces = hddInterfaceRepository.findAll();
        return hddInterfaceMapper.toListHddInterfaceResponse(hddInterfaces);
    }

    public HddInterfaceResponse getHddInterfaceById(String hddInterfaceId) {
        log.info("Getting HDD Interface with ID: {}", hddInterfaceId);

        HddInterface hddInterface = hddInterfaceRepository.findById(hddInterfaceId)
                .orElseThrow(() -> {
                    log.error("HDD Interface not found with ID: {}", hddInterfaceId);
                    return new RuntimeException("HDD Interface not found with id: " + hddInterfaceId);
                });

        return hddInterfaceMapper.toHddInterfaceResponse(hddInterface);
    }

    public HddInterfaceResponse updateHddInterface(HddInterfaceRequest request, String hddInterfaceId) {
        log.info("Updating HDD Interface with ID: {}", hddInterfaceId);

        HddInterface hddInterface = hddInterfaceRepository.findById(hddInterfaceId)
                .orElseThrow(() -> {
                    log.error("HDD Interface not found with ID: {}", hddInterfaceId);
                    return new RuntimeException("HDD Interface not found with id: " + hddInterfaceId);
                });

        hddInterfaceMapper.updateHddInterface(hddInterface, request);
        HddInterface updatedHddInterface = hddInterfaceRepository.save(hddInterface);

        log.info("HDD Interface updated successfully with ID: {}", updatedHddInterface.getId());
        return hddInterfaceMapper.toHddInterfaceResponse(updatedHddInterface);
    }

    public void deleteHddInterface(String hddInterfaceId) {
        log.info("Deleting HDD Interface with ID: {}", hddInterfaceId);

        HddInterface hddInterface = hddInterfaceRepository.findById(hddInterfaceId)
                .orElseThrow(() -> {
                    log.error("HDD Interface not found with ID: {}", hddInterfaceId);
                    return new RuntimeException("HDD Interface not found with id: " + hddInterfaceId);
                });

        hddInterfaceRepository.delete(hddInterface);
        log.info("HDD Interface deleted successfully with ID: {}", hddInterfaceId);
    }
}
