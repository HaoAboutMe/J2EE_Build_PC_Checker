package com.j2ee.buildpcchecker.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "pcie_version")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PcieVersion {

    @Id
    String id; // PCIE_3, PCIE_4, PCIE_5

    @Column(nullable = false)
    String name; // PCIe 3.0, PCIe 4.0, PCIe 5.0
}

