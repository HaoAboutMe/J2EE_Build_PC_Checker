package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.SsdTypeRequest;
import com.j2ee.buildpcchecker.dto.response.SsdTypeResponse;
import com.j2ee.buildpcchecker.entity.SsdType;
import com.j2ee.buildpcchecker.exception.AppException;
import com.j2ee.buildpcchecker.exception.ErrorCode;
import com.j2ee.buildpcchecker.mapper.SsdTypeMapper;
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
public class SsdTypeService {

    SsdTypeRepository ssdTypeRepository;
    SsdTypeMapper ssdTypeMapper;

    public SsdTypeResponse createSsdType(SsdTypeRequest request) {
        log.info("Creating new SSD Type: {}", request.getId());

        if (ssdTypeRepository.existsById(request.getId())) {
            throw new AppException(ErrorCode.SSD_TYPE_ALREADY_EXISTS);
        }

        SsdType ssdType = ssdTypeMapper.toSsdType(request);
        SsdType savedSsdType = ssdTypeRepository.save(ssdType);

        log.info("SSD Type created successfully with ID: {}", savedSsdType.getId());
        return ssdTypeMapper.toSsdTypeResponse(savedSsdType);
    }

    public List<SsdTypeResponse> getAllSsdTypes() {
        log.info("Getting all SSD Types");
        List<SsdType> ssdTypes = ssdTypeRepository.findAll();
        return ssdTypeMapper.toListSsdTypeResponse(ssdTypes);
    }

    public SsdTypeResponse getSsdTypeById(String ssdTypeId) {
        log.info("Getting SSD Type with ID: {}", ssdTypeId);

        SsdType ssdType = ssdTypeRepository.findById(ssdTypeId)
                .orElseThrow(() -> {
                    log.error("SSD Type not found with ID: {}", ssdTypeId);
                    return new AppException(ErrorCode.SSD_TYPE_NOT_FOUND);
                });

        return ssdTypeMapper.toSsdTypeResponse(ssdType);
    }

    public SsdTypeResponse updateSsdType(SsdTypeRequest request, String ssdTypeId) {
        log.info("Updating SSD Type with ID: {}", ssdTypeId);

        SsdType ssdType = ssdTypeRepository.findById(ssdTypeId)
                .orElseThrow(() -> {
                    log.error("SSD Type not found with ID: {}", ssdTypeId);
                    return new AppException(ErrorCode.SSD_TYPE_NOT_FOUND);
                });

        ssdTypeMapper.updateSsdType(ssdType, request);
        SsdType updatedSsdType = ssdTypeRepository.save(ssdType);

        log.info("SSD Type updated successfully with ID: {}", updatedSsdType.getId());
        return ssdTypeMapper.toSsdTypeResponse(updatedSsdType);
    }

    public void deleteSsdType(String ssdTypeId) {
        log.info("Deleting SSD Type with ID: {}", ssdTypeId);

        SsdType ssdType = ssdTypeRepository.findById(ssdTypeId)
                .orElseThrow(() -> {
                    log.error("SSD Type not found with ID: {}", ssdTypeId);
                    return new AppException(ErrorCode.SSD_TYPE_NOT_FOUND);
                });

        ssdTypeRepository.delete(ssdType);
        log.info("SSD Type deleted successfully with ID: {}", ssdTypeId);
    }
}
