package com.projet.Gestion.API.Controllers;

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
public class EmployeeDto {
    private String chef ;
    private String nom;
    private String prenom;
    private int niveau;
    private String telIntern;
    private String email;
    private String nomdepartement;
}
