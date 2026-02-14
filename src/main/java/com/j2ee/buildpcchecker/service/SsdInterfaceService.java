package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.SsdInterfaceRequest;
import com.j2ee.buildpcchecker.dto.response.SsdInterfaceResponse;
import com.j2ee.buildpcchecker.entity.SsdInterface;
import com.j2ee.buildpcchecker.mapper.SsdInterfaceMapper;
import com.j2ee.buildpcchecker.repository.SsdInterfaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class SsdInterfaceService {

    SsdInterfaceRepository ssdInterfaceRepository;
    SsdInterfaceMapper ssdInterfaceMapper;

    public SsdInterfaceResponse createSsdInterface(SsdInterfaceRequest request) {
        log.info("Creating new SSD Interface: {}", request.getId());

        if (ssdInterfaceRepository.existsById(request.getId())) {
            throw new RuntimeException("SSD Interface already exists with id: " + request.getId());
        }

        SsdInterface ssdInterface = ssdInterfaceMapper.toSsdInterface(request);
        SsdInterface savedSsdInterface = ssdInterfaceRepository.save(ssdInterface);

        log.info("SSD Interface created successfully with ID: {}", savedSsdInterface.getId());
        return ssdInterfaceMapper.toSsdInterfaceResponse(savedSsdInterface);
    }

    public List<SsdInterfaceResponse> getAllSsdInterfaces() {
        log.info("Getting all SSD Interfaces");
        List<SsdInterface> ssdInterfaces = ssdInterfaceRepository.findAll();
        return ssdInterfaceMapper.toListSsdInterfaceResponse(ssdInterfaces);
    }

    public SsdInterfaceResponse getSsdInterfaceById(String ssdInterfaceId) {
        log.info("Getting SSD Interface with ID: {}", ssdInterfaceId);

        SsdInterface ssdInterface = ssdInterfaceRepository.findById(ssdInterfaceId)
                .orElseThrow(() -> {
                    log.error("SSD Interface not found with ID: {}", ssdInterfaceId);
                    return new RuntimeException("SSD Interface not found with id: " + ssdInterfaceId);
                });

        return ssdInterfaceMapper.toSsdInterfaceResponse(ssdInterface);
    }

    public SsdInterfaceResponse updateSsdInterface(SsdInterfaceRequest request, String ssdInterfaceId) {
        log.info("Updating SSD Interface with ID: {}", ssdInterfaceId);

        SsdInterface ssdInterface = ssdInterfaceRepository.findById(ssdInterfaceId)
                .orElseThrow(() -> {
                    log.error("SSD Interface not found with ID: {}", ssdInterfaceId);
                    return new RuntimeException("SSD Interface not found with id: " + ssdInterfaceId);
                });

        ssdInterfaceMapper.updateSsdInterface(ssdInterface, request);
        SsdInterface updatedSsdInterface = ssdInterfaceRepository.save(ssdInterface);

        log.info("SSD Interface updated successfully with ID: {}", updatedSsdInterface.getId());
        return ssdInterfaceMapper.toSsdInterfaceResponse(updatedSsdInterface);
    }

    public void deleteSsdInterface(String ssdInterfaceId) {
        log.info("Deleting SSD Interface with ID: {}", ssdInterfaceId);

        SsdInterface ssdInterface = ssdInterfaceRepository.findById(ssdInterfaceId)
                .orElseThrow(() -> {
                    log.error("SSD Interface not found with ID: {}", ssdInterfaceId);
                    return new RuntimeException("SSD Interface not found with id: " + ssdInterfaceId);
                });

        ssdInterfaceRepository.delete(ssdInterface);
        log.info("SSD Interface deleted successfully with ID: {}", ssdInterfaceId);
    }
}
