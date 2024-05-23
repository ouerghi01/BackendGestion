package com.projet.Gestion.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRep extends JpaRepository<Employee, String> {
    @Query(value = "Select * from Employee where nom = ?1 Limit 1", nativeQuery = true)
    Employee findByNom(String createur);

    @Query(value = "SELECT * FROM Employee e WHERE e.email = ?1 LIMIT 1", nativeQuery = true)
    Employee findByEmail(String email);

}
