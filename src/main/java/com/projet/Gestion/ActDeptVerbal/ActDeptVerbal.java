package com.projet.Gestion.ActDeptVerbal;

import com.projet.Gestion.Activitésdept.Activitesdept;
import com.projet.Gestion.ProcessVerbal.Procesverbal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActDeptVerbal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long numActDeptV;
    @OneToOne
    @JoinColumn(name = "numProcesV")
    private Procesverbal procesverbal;
    @OneToOne
    @JoinColumn(name = "numAct")
    private Activitesdept actDept;

}
