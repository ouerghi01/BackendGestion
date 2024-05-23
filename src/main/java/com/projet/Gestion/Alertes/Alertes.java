package com.projet.Gestion.Alertes;

import java.time.LocalDate;

import com.projet.Gestion.Activité.Activité;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ALERTES")
public class Alertes {
    @Id
    private String type;
    private LocalDate delai;
    @ManyToOne
    @JoinColumn(name = "numActivite")
    private Activité activite;

}
