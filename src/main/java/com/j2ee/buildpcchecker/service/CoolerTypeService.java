package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.CoolerTypeRequest;
import com.j2ee.buildpcchecker.dto.response.CoolerTypeResponse;
import com.j2ee.buildpcchecker.entity.CoolerType;
import com.j2ee.buildpcchecker.mapper.CoolerTypeMapper;
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
public class CoolerTypeService {

    CoolerTypeRepository coolerTypeRepository;
    CoolerTypeMapper coolerTypeMapper;

    public CoolerTypeResponse createCoolerType(CoolerTypeRequest request) {
        log.info("Creating new Cooler Type: {}", request.getId());

        if (coolerTypeRepository.existsById(request.getId())) {
            throw new RuntimeException("Cooler Type already exists with id: " + request.getId());
        }

        CoolerType coolerType = coolerTypeMapper.toCoolerType(request);
        CoolerType savedCoolerType = coolerTypeRepository.save(coolerType);

        log.info("Cooler Type created successfully with ID: {}", savedCoolerType.getId());
        return coolerTypeMapper.toCoolerTypeResponse(savedCoolerType);
    }

    public List<CoolerTypeResponse> getAllCoolerTypes() {
        log.info("Getting all Cooler Types");
        List<CoolerType> coolerTypes = coolerTypeRepository.findAll();
        return coolerTypeMapper.toListCoolerTypeResponse(coolerTypes);
    }

    public CoolerTypeResponse getCoolerTypeById(String coolerTypeId) {
        log.info("Getting Cooler Type with ID: {}", coolerTypeId);

        CoolerType coolerType = coolerTypeRepository.findById(coolerTypeId)
                .orElseThrow(() -> {
                    log.error("Cooler Type not found with ID: {}", coolerTypeId);
                    return new RuntimeException("Cooler Type not found with id: " + coolerTypeId);
                });

        return coolerTypeMapper.toCoolerTypeResponse(coolerType);
    }

    public CoolerTypeResponse updateCoolerType(CoolerTypeRequest request, String coolerTypeId) {
        log.info("Updating Cooler Type with ID: {}", coolerTypeId);

        CoolerType coolerType = coolerTypeRepository.findById(coolerTypeId)
                .orElseThrow(() -> {
                    log.error("Cooler Type not found with ID: {}", coolerTypeId);
                    return new RuntimeException("Cooler Type not found with id: " + coolerTypeId);
                });

        coolerTypeMapper.updateCoolerType(coolerType, request);
        CoolerType updatedCoolerType = coolerTypeRepository.save(coolerType);

        log.info("Cooler Type updated successfully with ID: {}", updatedCoolerType.getId());
        return coolerTypeMapper.toCoolerTypeResponse(updatedCoolerType);
    }

    public void deleteCoolerType(String coolerTypeId) {
        log.info("Deleting Cooler Type with ID: {}", coolerTypeId);

        CoolerType coolerType = coolerTypeRepository.findById(coolerTypeId)
                .orElseThrow(() -> {
                    log.error("Cooler Type not found with ID: {}", coolerTypeId);
                    return new RuntimeException("Cooler Type not found with id: " + coolerTypeId);
                });

        coolerTypeRepository.delete(coolerType);
        log.info("Cooler Type deleted successfully with ID: {}", coolerTypeId);
    }
}
