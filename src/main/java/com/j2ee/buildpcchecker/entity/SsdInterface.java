package com.j2ee.buildpcchecker.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "ssd_interface")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SsdInterface {

    @Id
    String id; // SATA_3, PCIE_3, PCIE_4, PCIE_5

    @Column(nullable = false)
    String name; // SATA III, PCIe 3.0 x4, PCIe 4.0 x4, PCIe 5.0 x4
}
