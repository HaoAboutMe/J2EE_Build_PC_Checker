package com.j2ee.buildpcchecker.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SsdResponse {

    String id;
    String name;
    SsdTypeResponse ssdType;
    String formFactor;
    SsdInterfaceResponse ssdInterface;
    Integer capacity;
    Integer tdp;
    String description;
}
