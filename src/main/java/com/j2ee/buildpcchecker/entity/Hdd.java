package com.j2ee.buildpcchecker.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "hdd")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hdd {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String name;

    @Column(name = "form_factor", nullable = false)
    String formFactor; // 3.5" / 2.5"

    @ManyToOne
    @JoinColumn(name = "hdd_interface_id", nullable = false)
    HddInterface hddInterface; // SATA_3 / SAS

    @Column(nullable = false)
    Integer capacity; // GB

    @Column(nullable = false)
    Integer tdp; // W

    @Column(columnDefinition = "TEXT")
    String description;
}
