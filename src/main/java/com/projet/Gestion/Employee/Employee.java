package com.projet.Gestion.Employee;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projet.Gestion.Absent.Absent;
import com.projet.Gestion.AgendaEmp.Agenda;
import com.projet.Gestion.Departement.Departement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")

    private String numEmploye;
    private String nom;
    private String prenom;
    private int niveau;
    private String telIntern;
    private String email;
    @ManyToOne
    @JoinColumn(name = "numDept")
    @JsonManagedReference
    private Departement departement;

    @OneToOne(mappedBy = "chef")
    @JsonBackReference

    private Departement departementChef;
    @OneToOne(mappedBy = "employee")
    private Agenda agenda;

    @OneToMany(mappedBy = "employe")
    private List<Absent> absences;

}
