package com.projet.Gestion.API.Controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.Gestion.ActDeptVerbal.ActDeptVerbal;
import com.projet.Gestion.ActDeptVerbal.ActDeptVerbalRep;
import com.projet.Gestion.Activité.ActiviteRep;
import com.projet.Gestion.Activité.Activité;
import com.projet.Gestion.Activité.TypeA;
import com.projet.Gestion.Activitésdept.Activitesdept;
import com.projet.Gestion.Activitésdept.ActivitesdeptRep;
import com.projet.Gestion.AgendaEmp.Agenda;
import com.projet.Gestion.AgendaEmp.AgendaRep;
import com.projet.Gestion.Alertes.Alertes;
import com.projet.Gestion.Alertes.AlertesRep;
import com.projet.Gestion.Departement.Departement;
import com.projet.Gestion.Departement.DepartementRep;
import com.projet.Gestion.Employee.Employee;
import com.projet.Gestion.Employee.EmployeeRep;
import com.projet.Gestion.ProcessVerbal.ProcesVerbalRep;
import com.projet.Gestion.ProcessVerbal.Procesverbal;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class Api {
    private final ActivitesdeptRep activitesdeptRep;
    private final ActDeptVerbalRep actDeptVerbalRep;
    private final ProcesVerbalRep procesVerbalRep;
    private final ActiviteRep activiteRep;
    private final EmployeeRep employeeRep;
    private final AgendaRep agendaRep;
    private final AlertesRep alertesRep;
    private final DepartementRep departementRep;

    @PostMapping("/registre")
    public ResponseEntity<String> register(@RequestBody EmployeeDto entity) {
        Employee employee = Employee.builder().nom(entity.getNom()).prenom(entity.getPrenom())
                .niveau(entity.getNiveau()).telIntern(entity.getTelIntern()).email(entity.getEmail()).build();
        if (employee != null) {
            employeeRep.save(employee);
        }
        return ResponseEntity.ok("Employee registered successfully");
    }

    @GetMapping("/employees")
    public List<Employee> getallemployees() {
        return employeeRep.findAll();
    }

    @PostMapping("/Login")
    public ResponseEntity<EmployeSucess> login(@RequestBody EmployeeLogin entity) {
        Employee employee = employeeRep.findByEmail(entity.getEmail());
        if (employee != null) {
            int niveau = employee.getNiveau();
            return ResponseEntity.ok(EmployeSucess.builder().nom(employee.getNom()).niveau(niveau).build());
        }
        return null;

    }

    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeDto employee) {
        Employee chef = employeeRep.findByNom(employee.getChef());
        if (chef == null || chef.getNiveau() != 1) {
            return ResponseEntity.badRequest().body(CHEF_NOT_FOUND);
        }
        Departement departement = chef.getDepartementChef();
        if (departement == null) {
            return ResponseEntity.badRequest().body("Department not found");
        } else {
            Employee emp = Employee.builder().nom(employee.getNom()).prenom(employee.getPrenom())
                    .niveau(employee.getNiveau()).departement(departement)
                    .telIntern(employee.getTelIntern()).email(employee.getEmail()).build();
            if (emp != null) {
                employeeRep.save(emp);
            }
        }
        return ResponseEntity.ok("Employee added successfully");
    }

    @PostMapping("/createChef")
    public ResponseEntity<String> Chef(@RequestBody EmployeeDto entity) {
        Employee chef = Employee.builder().nom(entity.getNom()).prenom(entity.getPrenom()).niveau(1).build();

        if (chef != null) {
            employeeRep.save(chef);
            return ResponseEntity.ok("Chef added successfully");
        }
        return ResponseEntity.badRequest().body(EMPLOYEE_NOT_FOUND);
    }

    private static final String EMPLOYEE_NOT_FOUND = "Employee not found";
    private static final String CHEF_NOT_FOUND = "CHef  not found";

    @PostMapping("/initialize-department")
    public ResponseEntity<String> initializeDepartment(@RequestBody DepartementDto dto) {
        Employee chef = employeeRep.findByNom(dto.getNomchef());
        if (chef == null) {
            return ResponseEntity.badRequest().body(EMPLOYEE_NOT_FOUND);
        } else {
            Departement departement = Departement.builder().nom(dto.getNomdepar()).chef(chef).build();
            if (departement != null) {
                departementRep.save(departement);
            }
        }
        return ResponseEntity.ok("Department initialized successfully");
    }

    @PostMapping("/processverbal")
    public ResponseEntity<String> createProcessVerbal(@RequestBody processverbal entity) {
        Procesverbal processVerbal = Procesverbal.builder().resum(entity.getResum()).build();
        if (processVerbal != null)
            procesVerbalRep.save(processVerbal);

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/professional-department")
    public ResponseEntity<String> createActivitesdept(@RequestBody ActiviteDto entity) {
        return getProcessVerbal(entity);
    }

    private ResponseEntity<String> getProcessVerbal(ActiviteDto entity) {
        String resum = entity.getResum();
        Procesverbal processVerbal = procesVerbalRep.findByResum(resum);
        if (processVerbal == null) {
            return ResponseEntity.badRequest().body("ProcessVerbal not found");
        } else {
            Activitesdept activitesdept = Activitesdept.builder().typeD(entity.getTypeD())
                    .descript(entity.getDescript())
                    .dateAct(entity.getDateAct()).hDébut(entity.getHDébut()).hFin(entity.getHFin())
                    .dateCreation(entity.getDateCreation()).createur(entity.getCreateur()).build();
            if (activitesdept != null) {
                activitesdeptRep.save(activitesdept);
            }
            ActDeptVerbal actDeptVerbal = ActDeptVerbal.builder().procesverbal(processVerbal)
                    .actDept(activitesdept).build();
            if (actDeptVerbal != null)
                actDeptVerbalRep.save(actDeptVerbal);

        }

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/Agenda")
    public String createAgenda(@RequestBody String entity) {

        return entity;
    }

    /* Personel */
    @PostMapping("/personal-activity")
    public ResponseEntity<String> createPersonalActivity(@RequestBody Personal activiteDto) {
        return InsertPersonalActivite(activiteDto);

    }

    @GetMapping("/personal-activity")
    public List<Activité> getPersonel(@RequestBody Superieur entity) {
        Employee superieur = employeeRep.findByNom(entity.getName());
        if (superieur != null && superieur.getNiveau() == 1) {
            return activiteRep.findActivitiesByVisibility(1);
        } else {
            return Collections.emptyList();
        }
    }

    private ResponseEntity<String> InsertPersonalActivite(Personal activiteDto) {
        Employee chef = employeeRep.findByNom(activiteDto.getCreateur());
        Departement chefDept = chef.getDepartement();
        if (chefDept != null) {
            Employee employee = employeeRep.findByNom(activiteDto.getTo_employee());
            if (employee != null) {
                Agenda agenda = Agenda.builder().employee(employee)
                        .datecreation(activiteDto.getAgendacreation()).build();
                if (agenda != null)
                    agendaRep.save(agenda);
                TypeA typeA = TypeA.valueOf(activiteDto.getTypeA());
                int lastid = activiteRep.findLastId();
                Activité activite = Activité.builder().typeA(typeA).numActivité(lastid + 1)
                        .description(activiteDto.getDescription())
                        .dateAct(activiteDto.getDateAct()).hDébut(activiteDto.getHDébut()).hFin(activiteDto.getHFin())
                        .dateCreation(activiteDto.getDateCreation()).agenda(agenda).createur(activiteDto.getCreateur())
                        .visible(1).build();
                if (activite != null) {
                    activiteRep.save(activite);
                    Alertes alertes = Alertes.builder().type(activiteDto.getTypealerte())
                            .delai(activiteDto.getDatealerte()).activite(activite).build();
                    if (alertes != null) {
                        alertesRep.save(alertes);
                    }
                }

            } else {
                return ResponseEntity.badRequest().body(EMPLOYEE_NOT_FOUND);
            }
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.badRequest().body(EMPLOYEE_NOT_FOUND);
        }
    }

    /* non-professional */
    @PostMapping("/non-professional")
    public ResponseEntity<String> createNonProfessionalActivity(@RequestBody SimpleActivite activiteDto) {
        return NonProfessional(activiteDto);

    }

    @GetMapping("/non-professional")
    public List<Activité> get(@RequestParam String param) {
        return activiteRep.findActivitiesByVisibility(0);
    }

    private ResponseEntity<String> NonProfessional(SimpleActivite activiteDto) {
        Employee employee = employeeRep.findByNom(activiteDto.getCreateur());
        if (employee != null) {

            TypeA typeA = TypeA.valueOf(activiteDto.getTypeA());
            int lastid = activiteRep.findLastId();
            Activité activite = Activité.builder().typeA(typeA).numActivité(lastid + 1)
                    .description(activiteDto.getDescription())
                    .dateAct(activiteDto.getDateAct()).hDébut(activiteDto.getHDébut()).hFin(activiteDto.getHFin())
                    .dateCreation(activiteDto.getDateCreation()).createur(activiteDto.getCreateur())
                    .visible(0).build();
            if (activite != null) {
                activiteRep.save(activite);
            }
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.badRequest().body("Employee not found");
        }
    }

}
