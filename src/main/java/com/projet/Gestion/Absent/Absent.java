package com.projet.Gestion.Absent;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projet.Gestion.Activitésdept.Activitesdept;
import com.projet.Gestion.Employee.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ABSENT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Absent {
    @Id
    private Long id;
    @ManyToOne

    @JoinColumn(name = "numEmploye")
    @JsonBackReference
    private Employee employe;
    @ManyToOne

    @JoinColumn(name = "numActDept")
    @JsonManagedReference
    private Activitesdept activitesdept;
    private String motif;

}
