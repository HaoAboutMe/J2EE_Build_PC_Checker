package com.j2ee.buildpcchecker.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HddUpdateRequest {

    String name;
    String formFactor;
    String hddInterfaceId;
    Integer capacity;
    Integer tdp;
    String description;
}
