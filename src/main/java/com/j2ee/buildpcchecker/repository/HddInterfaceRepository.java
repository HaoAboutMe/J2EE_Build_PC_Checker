package com.j2ee.buildpcchecker.repository;

import com.j2ee.buildpcchecker.entity.HddInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HddInterfaceRepository extends JpaRepository<HddInterface, String> {
}
