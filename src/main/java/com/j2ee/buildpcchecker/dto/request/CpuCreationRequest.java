package com.j2ee.buildpcchecker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CpuCreationRequest {

    @NotBlank(message = "CPU name is required")
    String name;

    @NotBlank(message = "Socket ID is required")
    String socketId;

    Integer vrmMin;

    @NotNull(message = "iGPU field is required")
    Boolean igpu;

    @NotNull(message = "TDP is required")
    Integer tdp;

    @NotBlank(message = "PCIe Version ID is required")
    String pcieVersionId;

    @NotNull(message = "Score is required")
    Integer score;

    String description;
}
