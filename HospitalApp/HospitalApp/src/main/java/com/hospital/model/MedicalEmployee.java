package com.hospital.model;

import com.hospital.util.MedicalType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalEmployee {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer medicalEmployeeId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private MedicalType medicalType;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "medicalTeam")
    private List<Patient> patientList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamLeader")
    private List<MedicalEmployee> team;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private MedicalEmployee teamLeader;

    public void addPatient(Patient patient) {
        if (patientList == null) {             /// daca lista e cumva goala o initializez;
            patientList = new ArrayList<>();
        }

        patientList.add(patient);
    }
}
