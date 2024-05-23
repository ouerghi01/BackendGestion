package com.projet.Gestion.Departement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRep extends JpaRepository<Departement, Long> {

    @Query(value = "SELECT d FROM Departement d WHERE d.nom = :nomdep", nativeQuery = true)
    Departement findByName(@Param("nomdep") String nomdep);

}
