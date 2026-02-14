package com.j2ee.buildpcchecker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "cooler_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoolerType {

    @Id
    @Column(nullable = false)
    String id; // AIR, AIO

    @Column(nullable = false)
    String name;
}
