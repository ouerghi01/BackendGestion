package com.projet.Gestion.ProcessVerbal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesVerbalRep extends JpaRepository<Procesverbal, Long> {
    @Query(value = "Select * from Procesverbal where resum = ?1 Limit 1", nativeQuery = true)
    Procesverbal findByResum(String resum);

}