package com.j2ee.buildpcchecker.repository;

import com.j2ee.buildpcchecker.entity.SsdType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SsdTypeRepository extends JpaRepository<SsdType, String> {
}
