package com.j2ee.buildpcchecker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RamTypeRequest {

    @NotBlank(message = "RAM_TYPE_ID_VALUE_REQUIRED")
    String id;

    @NotBlank(message = "RAM_TYPE_NAME_REQUIRED")
    String name;
}

