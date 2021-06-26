package com.hospital.builder;

import com.hospital.model.MedicalEmployee;
import com.hospital.model.MedicalRecord;
import com.hospital.model.Patient;
import com.hospital.service.IOService;
import dao.MedicalEmployeeDao;
import dao.PatientDao;

import java.time.LocalDateTime;

public class MedicalRecordBuilder {
    private IOService ioService;
    private MedicalEmployeeDao medicalEmployeeDao;
    private PatientDao patientDao;

    public MedicalRecordBuilder(IOService ioService, MedicalEmployeeDao medicalEmployeeDao, PatientDao patientDao) {
        this.ioService = ioService;
        this.medicalEmployeeDao = medicalEmployeeDao;
        this.patientDao = patientDao;
    }

    public MedicalRecord create(Integer medicalEmployeeId) {
        String cnp = ioService.getGenericPatientData("cnp");
        String information = ioService.getGenericPatientData("information");


        MedicalEmployee medicalEmployee = medicalEmployeeDao.findById(MedicalEmployee.class, medicalEmployeeId);
        Patient patient = patientDao.findByCnp(cnp);

        return MedicalRecord.builder()
                .information(information)
                .localDateTime(LocalDateTime.now())
                .patient(patient)
                .medicalEmployee(medicalEmployee)
                .build();
    }
}
