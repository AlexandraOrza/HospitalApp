package com.hospital.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder            // builder din Lombok
@NoArgsConstructor  // builder din Lombok fara argumente
@AllArgsConstructor

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    private String firstName;
    private String lastName;
    private String email;
    private String cnp;

    @ManyToMany
    @JoinTable(name = "patient_medicalEmployee",
            joinColumns = @JoinColumn(name = "patientId"),
            inverseJoinColumns = @JoinColumn(name = "medicalEmployeeId"))
    @ToString.Exclude
    private List<MedicalEmployee> medicalTeam;




    public void addMedic(MedicalEmployee medicalEmployee) {
        if (medicalTeam == null){
            medicalTeam = new ArrayList<>();
        }
        medicalTeam.add(medicalEmployee);
    }
}
