package com.j2ee.buildpcchecker.repository;

import com.j2ee.buildpcchecker.entity.RamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RamTypeRepository extends JpaRepository<RamType, String> {
}

