package com.j2ee.buildpcchecker.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "form_factor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormFactor {

    @Id
    @Column(nullable = false)
    String id; // FF_2_5, FF_3_5, M2_2280, M2_2260, M2_2242

    @Column(nullable = false)
    String name; // 2.5", 3.5", M.2 2280, M.2 2260, M.2 2242
}

