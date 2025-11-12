package com.may.a03.repository;

import com.may.a03.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    Optional<Disease> findByCode(String code);
    List<Disease> findByNameContainingIgnoreCase(String name);
}
