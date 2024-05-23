package com.projet.Gestion.API.Controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActiviteDto {
    private String typeD;
    private String descript;
    private LocalDate dateAct;
    private LocalTime hDébut;
    private LocalTime hFin;
    private LocalDate dateCreation;
    private String createur;
    private String resum;

}
