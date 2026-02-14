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
public class HddCreationRequest {

    @NotBlank(message = "HDD_NAME_REQUIRED")
    String name;

    @NotBlank(message = "HDD_FORM_FACTOR_REQUIRED")
    String formFactor; // 3.5" / 2.5"

    @NotBlank(message = "HDD_INTERFACE_ID_REQUIRED")
    String hddInterfaceId; // SATA_3 / SAS

    @NotNull(message = "HDD_CAPACITY_REQUIRED")
    Integer capacity; // GB

    @NotNull(message = "HDD_TDP_REQUIRED")
    Integer tdp; // W

    String description;
}
