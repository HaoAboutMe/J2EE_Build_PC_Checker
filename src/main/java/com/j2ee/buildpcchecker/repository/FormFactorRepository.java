package com.j2ee.buildpcchecker.repository;

import com.j2ee.buildpcchecker.entity.FormFactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormFactorRepository extends JpaRepository<FormFactor, String> {
}

