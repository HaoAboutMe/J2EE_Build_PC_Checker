package com.j2ee.buildpcchecker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "hdd_interface")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HddInterface {

    @Id
    @Column(nullable = false)
    String id; // SATA_3, SAS

    @Column(nullable = false)
    String name;
}
