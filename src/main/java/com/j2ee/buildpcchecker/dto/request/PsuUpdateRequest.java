package com.j2ee.buildpcchecker.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PsuUpdateRequest {

    String name;
    Integer wattage;
    String efficiency;
    String pcieConnectorId;
    Integer sataConnector;
    String description;
}
