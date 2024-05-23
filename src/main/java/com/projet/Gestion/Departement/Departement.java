package com.projet.Gestion.Departement;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projet.Gestion.Agendadept.Agendadept;
import com.projet.Gestion.Employee.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;
    private String nom;
    @OneToMany(mappedBy = "departement")
    @JsonBackReference
    private List<Employee> employees;
    @OneToOne
    @JoinColumn(name = "numchef")
    @JsonManagedReference
    private Employee chef;
    @OneToOne
    @JoinColumn(name = "numAgenda")
    @JsonManagedReference
    private Agendadept agendadept;
}
