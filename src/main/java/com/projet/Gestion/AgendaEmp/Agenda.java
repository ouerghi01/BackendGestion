package com.projet.Gestion.AgendaEmp;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projet.Gestion.Activité.Activité;
import com.projet.Gestion.Employee.Employee;

import jakarta.persistence.Entity;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Agenda {
    @Id
    private Long numAgenda;
    private Date datecreation;
    @OneToOne
    @JoinColumn(name = "numEmploye")
    @JsonBackReference
    private Employee employee;
    @OneToMany(mappedBy = "agenda")
    @JsonBackReference
    private List<Activité> activités;

}
