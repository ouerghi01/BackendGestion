package com.projet.Gestion.Activitésdept;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projet.Gestion.Absent.Absent;
import com.projet.Gestion.ActDeptVerbal.ActDeptVerbal;
import com.projet.Gestion.Agendadept.Agendadept;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACTIVITESDEPT")
public class Activitesdept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int numAct;
    private String typeD;
    private String descript;
    private LocalDate dateAct;
    private LocalTime hDébut;
    private LocalTime hFin;
    private LocalDate dateCreation;
    private String createur;
    @ManyToOne
    @JoinColumn(name = "numAgenda")
    @JsonBackReference
    private Agendadept agendadept;
    @OneToOne(mappedBy = "actDept")
    @JsonBackReference
    private ActDeptVerbal actDeptVerbal;
    @OneToMany(mappedBy = "activitesdept")
    @JsonBackReference
    private List<Absent> absences;

}
