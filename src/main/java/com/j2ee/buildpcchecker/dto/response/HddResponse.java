package com.j2ee.buildpcchecker.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HddResponse {

    String id;
    String name;
    String formFactor;
    HddInterfaceResponse hddInterface;
    Integer capacity;
    Integer tdp;
    String description;
}
