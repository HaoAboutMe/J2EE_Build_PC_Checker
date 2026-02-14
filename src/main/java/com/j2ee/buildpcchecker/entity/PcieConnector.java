package com.j2ee.buildpcchecker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "pcie_connector")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PcieConnector {

    @Id
    @Column(nullable = false)
    String id; // 2X8PIN, 3X8PIN, 12VHPWR, 16PIN

    @Column(nullable = false)
    String name;
}
