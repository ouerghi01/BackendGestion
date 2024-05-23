package com.projet.Gestion.API.Controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Personal {
    private String typealerte;
    private LocalDate datealerte;
    private Date Agendacreation;
    private String typeA;
    private String description;
    private LocalDate dateAct;
    private LocalTime hDébut;
    private LocalTime hFin;
    private LocalDate dateCreation;
    private String createur;
    private String To_employee;

}
