package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.InterfaceTypeRequest;
import com.j2ee.buildpcchecker.dto.response.InterfaceTypeResponse;
import com.j2ee.buildpcchecker.entity.InterfaceType;
import com.j2ee.buildpcchecker.exception.AppException;
import com.j2ee.buildpcchecker.exception.ErrorCode;
import com.j2ee.buildpcchecker.mapper.InterfaceTypeMapper;
import com.j2ee.buildpcchecker.repository.InterfaceTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class InterfaceTypeService {

    InterfaceTypeRepository interfaceTypeRepository;
    InterfaceTypeMapper interfaceTypeMapper;

    /**
     * Create a new Interface Type
     * @param request Interface Type creation request
     * @return InterfaceTypeResponse
     */
    public InterfaceTypeResponse createInterfaceType(InterfaceTypeRequest request) {
        log.info("Creating new Interface Type: {}", request.getId());

        if (interfaceTypeRepository.existsById(request.getId())) {
            throw new RuntimeException("Interface Type already exists with id: " + request.getId());
        }

        InterfaceType interfaceType = interfaceTypeMapper.toInterfaceType(request);
        InterfaceType savedInterfaceType = interfaceTypeRepository.save(interfaceType);

        log.info("Interface Type created successfully with ID: {}", savedInterfaceType.getId());
        return interfaceTypeMapper.toInterfaceTypeResponse(savedInterfaceType);
    }

    /**
     * Get all Interface Types
     * @return List of InterfaceTypeResponse
     */
    public List<InterfaceTypeResponse> getAllInterfaceTypes() {
        log.info("Getting all Interface Types");
        List<InterfaceType> interfaceTypes = interfaceTypeRepository.findAll();
        return interfaceTypeMapper.toListInterfaceTypeResponse(interfaceTypes);
    }

    /**
     * Get Interface Type by ID
     * @param interfaceTypeId Interface Type ID
     * @return InterfaceTypeResponse
     */
    public InterfaceTypeResponse getInterfaceTypeById(String interfaceTypeId) {
        log.info("Getting Interface Type with ID: {}", interfaceTypeId);

        InterfaceType interfaceType = interfaceTypeRepository.findById(interfaceTypeId)
                .orElseThrow(() -> {
                    log.error("Interface Type not found with ID: {}", interfaceTypeId);
                    return new AppException(ErrorCode.INTERFACE_TYPE_NOT_FOUND);
                });

        return interfaceTypeMapper.toInterfaceTypeResponse(interfaceType);
    }

    /**
     * Update Interface Type by ID
     * @param request Interface Type update request
     * @param interfaceTypeId Interface Type ID
     * @return InterfaceTypeResponse
     */
    public InterfaceTypeResponse updateInterfaceType(InterfaceTypeRequest request, String interfaceTypeId) {
        log.info("Updating Interface Type with ID: {}", interfaceTypeId);

        InterfaceType interfaceType = interfaceTypeRepository.findById(interfaceTypeId)
                .orElseThrow(() -> {
                    log.error("Interface Type not found with ID: {}", interfaceTypeId);
                    return new AppException(ErrorCode.INTERFACE_TYPE_NOT_FOUND);
                });

        interfaceTypeMapper.updateInterfaceType(interfaceType, request);
        InterfaceType updatedInterfaceType = interfaceTypeRepository.save(interfaceType);

        log.info("Interface Type updated successfully with ID: {}", updatedInterfaceType.getId());
        return interfaceTypeMapper.toInterfaceTypeResponse(updatedInterfaceType);
    }

    /**
     * Delete Interface Type by ID
     * @param interfaceTypeId Interface Type ID
     */
    public void deleteInterfaceType(String interfaceTypeId) {
        log.info("Deleting Interface Type with ID: {}", interfaceTypeId);

        InterfaceType interfaceType = interfaceTypeRepository.findById(interfaceTypeId)
                .orElseThrow(() -> {
                    log.error("Interface Type not found with ID: {}", interfaceTypeId);
                    return new AppException(ErrorCode.INTERFACE_TYPE_NOT_FOUND);
                });

        interfaceTypeRepository.delete(interfaceType);
        log.info("Interface Type deleted successfully with ID: {}", interfaceTypeId);
    }
}

