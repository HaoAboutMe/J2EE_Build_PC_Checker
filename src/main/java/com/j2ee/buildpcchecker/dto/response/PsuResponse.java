package com.j2ee.buildpcchecker.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PsuResponse {

    String id;
    String name;
    Integer wattage;
    String efficiency;
    PcieConnectorResponse pcieConnector;
    Integer sataConnector;
    String description;
}
