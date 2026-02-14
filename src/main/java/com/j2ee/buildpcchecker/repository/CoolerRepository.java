package com.j2ee.buildpcchecker.repository;

import com.j2ee.buildpcchecker.entity.Cooler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoolerRepository extends JpaRepository<Cooler, String> {
    boolean existsByName(String name);
}
