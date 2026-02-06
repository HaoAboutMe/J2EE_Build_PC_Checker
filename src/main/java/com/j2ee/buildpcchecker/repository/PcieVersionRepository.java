package com.j2ee.buildpcchecker.repository;

import com.j2ee.buildpcchecker.entity.PcieVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcieVersionRepository extends JpaRepository<PcieVersion, String> {
}

