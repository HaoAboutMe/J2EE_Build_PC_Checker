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

    @NotBlank(message = "RAM Type ID is required")
    String id;

    @NotBlank(message = "RAM Type name is required")
    String name;
}

