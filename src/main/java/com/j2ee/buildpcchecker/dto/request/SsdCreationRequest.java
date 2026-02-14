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
public class SsdCreationRequest {

    @NotBlank(message = "SSD_NAME_REQUIRED")
    String name;

    @NotBlank(message = "SSD_TYPE_ID_REQUIRED")
    String ssdTypeId; // SATA / NVME

    @NotBlank(message = "SSD_FORM_FACTOR_REQUIRED")
    String formFactor; // 2.5", M.2 2280

    @NotBlank(message = "SSD_INTERFACE_ID_REQUIRED")
    String ssdInterfaceId; // SATA_3 / PCIE_4 / PCIE_5

    @NotNull(message = "SSD_CAPACITY_REQUIRED")
    Integer capacity; // GB

    @NotNull(message = "SSD_TDP_REQUIRED")
    Integer tdp; // W

    String description;
}
