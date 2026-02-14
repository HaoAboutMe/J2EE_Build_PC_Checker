package com.j2ee.buildpcchecker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "interface_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InterfaceType {

    @Id
    @Column(nullable = false)
    String id; // SATA_3, SAS, PCIE_3, PCIE_4, PCIE_5

    @Column(nullable = false)
    String name; // SATA III, SAS, PCIe 3.0 x4, PCIe 4.0 x4, PCIe 5.0 x4
}

