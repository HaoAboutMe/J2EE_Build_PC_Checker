package com.j2ee.buildpcchecker.mapper;

import com.j2ee.buildpcchecker.dto.request.SocketRequest;
import com.j2ee.buildpcchecker.dto.response.SocketResponse;
import com.j2ee.buildpcchecker.entity.Socket;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SocketMapper {

    Socket toSocket(SocketRequest request);

    SocketResponse toSocketResponse(Socket socket);

    List<SocketResponse> toListSocketResponse(List<Socket> sockets);

    void updateSocket(@MappingTarget Socket socket, SocketRequest request);
}

