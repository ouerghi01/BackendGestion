package com.projet.Gestion.Absent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AbsentRep extends JpaRepository<Absent, Long> {
@Query(value = "SELECT * FROM Absent WHERE id = ?1", nativeQuery = true)
Absent findAbsentById(Long id);

}
