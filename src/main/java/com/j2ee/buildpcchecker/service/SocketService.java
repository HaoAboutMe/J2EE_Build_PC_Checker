package com.j2ee.buildpcchecker.service;

import com.j2ee.buildpcchecker.dto.request.SocketRequest;
import com.j2ee.buildpcchecker.dto.response.SocketResponse;
import com.j2ee.buildpcchecker.entity.Socket;
import com.j2ee.buildpcchecker.exception.AppException;
import com.j2ee.buildpcchecker.exception.ErrorCode;
import com.j2ee.buildpcchecker.mapper.SocketMapper;
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
public class SocketService {

    SocketRepository socketRepository;
    SocketMapper socketMapper;

    /**
     * Create a new Socket
     * @param request Socket creation request
     * @return SocketResponse
     */
    public SocketResponse createSocket(SocketRequest request) {
        log.info("Creating new Socket: {}", request.getId());

        // Check if socket already exists
        if (socketRepository.existsById(request.getId())) {
            log.error("Socket already exists with ID: {}", request.getId());
            throw new AppException(ErrorCode.SOCKET_ALREADY_EXISTS);
        }

        Socket socket = socketMapper.toSocket(request);
        Socket savedSocket = socketRepository.save(socket);

        log.info("Socket created successfully with ID: {}", savedSocket.getId());
        return socketMapper.toSocketResponse(savedSocket);
    }

    /**
     * Get all Sockets
     * @return List of SocketResponse
     */
    public List<SocketResponse> getAllSockets() {
        log.info("Getting all Sockets");
        List<Socket> sockets = socketRepository.findAll();
        return socketMapper.toListSocketResponse(sockets);
    }

    /**
     * Get Socket by ID
     * @param socketId Socket ID
     * @return SocketResponse
     */
    public SocketResponse getSocketById(String socketId) {
        log.info("Getting Socket with ID: {}", socketId);

        Socket socket = socketRepository.findById(socketId)
                .orElseThrow(() -> {
                    log.error("Socket not found with ID: {}", socketId);
                    return new AppException(ErrorCode.SOCKET_NOT_FOUND);
                });

        return socketMapper.toSocketResponse(socket);
    }

    /**
     * Update Socket by ID
     * @param request Socket update request
     * @param socketId Socket ID
     * @return SocketResponse
     */
    public SocketResponse updateSocket(SocketRequest request, String socketId) {
        log.info("Updating Socket with ID: {}", socketId);

        Socket socket = socketRepository.findById(socketId)
                .orElseThrow(() -> {
                    log.error("Socket not found with ID: {}", socketId);
                    return new AppException(ErrorCode.SOCKET_NOT_FOUND);
                });

        socketMapper.updateSocket(socket, request);
        Socket updatedSocket = socketRepository.save(socket);

        log.info("Socket updated successfully with ID: {}", updatedSocket.getId());
        return socketMapper.toSocketResponse(updatedSocket);
    }

    /**
     * Delete Socket by ID
     * @param socketId Socket ID
     */
    public void deleteSocket(String socketId) {
        log.info("Deleting Socket with ID: {}", socketId);

        Socket socket = socketRepository.findById(socketId)
                .orElseThrow(() -> {
                    log.error("Socket not found with ID: {}", socketId);
                    return new AppException(ErrorCode.SOCKET_NOT_FOUND);
                });

        socketRepository.delete(socket);
        log.info("Socket deleted successfully with ID: {}", socketId);
    }
}

