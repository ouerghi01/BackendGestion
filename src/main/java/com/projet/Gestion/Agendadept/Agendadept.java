package com.projet.Gestion.Agendadept;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projet.Gestion.Activitésdept.Activitesdept;
import com.projet.Gestion.Departement.Departement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Agendadept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numAgenda;
    private Date dateMAJ;
    @OneToOne(mappedBy = "agendadept")
    @JsonBackReference
    private Departement departement;
    @OneToMany(mappedBy = "agendadept")
    @JsonManagedReference
    private List<Activitesdept> activitesdept;

}
