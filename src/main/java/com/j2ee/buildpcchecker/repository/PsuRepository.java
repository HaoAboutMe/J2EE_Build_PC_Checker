package com.j2ee.buildpcchecker.repository;

import com.j2ee.buildpcchecker.entity.Psu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsuRepository extends JpaRepository<Psu, String> {
    boolean existsByName(String name);
}
