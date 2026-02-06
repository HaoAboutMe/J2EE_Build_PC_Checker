package com.j2ee.buildpcchecker.repository;

import com.j2ee.buildpcchecker.entity.Ram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RamRepository extends JpaRepository<Ram, String> {
}

