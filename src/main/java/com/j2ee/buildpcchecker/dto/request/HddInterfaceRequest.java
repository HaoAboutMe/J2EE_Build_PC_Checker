package com.j2ee.buildpcchecker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HddInterfaceRequest {

    @NotBlank(message = "HDD_INTERFACE_ENTITY_ID_REQUIRED")
    String id;

    @NotBlank(message = "HDD_INTERFACE_NAME_REQUIRED")
    String name;
}
