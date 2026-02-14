package com.j2ee.buildpcchecker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PcieConnectorRequest {

    @NotBlank(message = "PCIE_CONNECTOR_ID_REQUIRED")
    String id;

    @NotBlank(message = "PCIE_CONNECTOR_NAME_REQUIRED")
    String name;
}
