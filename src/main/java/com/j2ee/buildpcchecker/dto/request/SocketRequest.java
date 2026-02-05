package com.j2ee.buildpcchecker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocketRequest {

    @NotBlank(message = "Socket ID is required")
    String id;

    @NotBlank(message = "Socket name is required")
    String name;
}
