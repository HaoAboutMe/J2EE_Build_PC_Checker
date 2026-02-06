package com.j2ee.buildpcchecker.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "ram_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RamType {

    @Id
    String id; // DDR4, DDR5

    @Column(nullable = false)
    String name;
}

