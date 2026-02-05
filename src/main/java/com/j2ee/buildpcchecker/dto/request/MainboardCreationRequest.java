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
public class MainboardCreationRequest {

    @NotBlank(message = "Mainboard name is required")
    String name;

    @NotBlank(message = "Socket ID is required")
    String socketId;

    @NotNull(message = "VRM phase is required")
    Integer vrmPhase;

    @NotNull(message = "CPU TDP support is required")
    Integer cpuTdpSupport;

    @NotBlank(message = "RAM Type ID is required")
    String ramTypeId;

    @NotNull(message = "RAM bus max is required")
    Integer ramBusMax;

    @NotNull(message = "RAM slot is required")
    Integer ramSlot;

    @NotNull(message = "RAM max capacity is required")
    Integer ramMaxCapacity;

    @NotBlank(message = "Size is required")
    String size;

    @NotBlank(message = "PCIe VGA Version ID is required")
    String pcieVgaVersionId;

    Integer m2Slot;
    Integer sataSlot;
    String description;
}
