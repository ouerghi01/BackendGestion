package com.projet.Gestion.Activité;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiviteRep extends JpaRepository<Activité, Integer> {
    @Query(value = "SELECT MAX(numActivité) FROM Activité", nativeQuery = true)
    int findLastId();

    @Query(value = "SELECT * FROM Activité WHERE visible = :visible", nativeQuery = true)
    List<Activité> findActivitiesByVisibility(@Param("visible") int isVisible);

}
