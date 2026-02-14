package com.j2ee.buildpcchecker.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "ssd")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ssd {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String name;

    @ManyToOne
    @JoinColumn(name = "ssd_type_id", nullable = false)
    SsdType ssdType; // SATA / NVME

    @Column(name = "form_factor", nullable = false)
    String formFactor; // 2.5", M.2 2280

    @ManyToOne
    @JoinColumn(name = "ssd_interface_id", nullable = false)
    SsdInterface ssdInterface; // SATA III / PCIe 3.0 / PCIe 4.0

    @Column(nullable = false)
    Integer capacity; // GB

    @Column(nullable = false)
    Integer tdp; // W

    @Column(columnDefinition = "TEXT")
    String description;
}
