package com.j2ee.buildpcchecker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InterfaceTypeRequest {

    @NotBlank(message = "INTERFACE_TYPE_ENTITY_ID_REQUIRED")
    String id;

    @NotBlank(message = "INTERFACE_TYPE_NAME_REQUIRED")
    String name;
}

