package com.projet.Gestion.Activité;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projet.Gestion.AgendaEmp.Agenda;
import com.projet.Gestion.Alertes.Alertes;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Activité {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int numActivité;
    @Enumerated(EnumType.STRING)
    private TypeA typeA;
    private String description;
    private LocalDate dateAct;
    private LocalTime hDébut;
    private LocalTime hFin;
    private LocalDate dateCreation;
    private String createur;
    private int visible;
    @ManyToOne
    @JoinColumn(name = "numAgenda")
    @JsonManagedReference
    private Agenda agenda;
    @OneToMany(mappedBy = "activite")
    @JsonManagedReference
    private List<Alertes> alertes;
}
