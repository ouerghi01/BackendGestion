package com.projet.Gestion.API.Controllers;

import java.time.LocalDate;
import java.time.LocalTime;

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

public class SimpleActivite {
    private String typeA;
    private String description;
    private LocalDate dateAct;
    private LocalTime hDébut;
    private LocalTime hFin;
    private LocalDate dateCreation;
    private String createur;
}
