package com.hospital.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Getter
//@Setter
@Builder
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data // le contine pe cele de mai sus
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer medicalRecordId;
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn
    private MedicalEmployee medicalEmployee;

    @ManyToOne
    @JoinColumn
    private Patient patient;

    private String information;

}
