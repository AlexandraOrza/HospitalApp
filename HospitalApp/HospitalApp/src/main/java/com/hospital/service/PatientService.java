package com.hospital.service;

import com.hospital.builder.MedicalRecordBuilder;
import com.hospital.builder.PatientBuilder;
import com.hospital.model.MedicalRecord;
import com.hospital.model.Patient;
import com.hospital.util.UserDetails;
import dao.MedicalEmployeeDao;
import dao.MedicalRecordDao;
import dao.PatientDao;

import java.util.List;

public class PatientService {

    private PatientBuilder patientBuilder;
    private PatientDao patientDao;
    private IOService ioService;
    private MedicalRecordBuilder medicalRecordBuilder;
    private MedicalRecordDao medicalRecordDao;


    public PatientService(IOService ioService, MedicalEmployeeDao medicalEmployeeDao) {
        this.ioService = ioService;
        this.patientBuilder = new PatientBuilder(ioService, medicalEmployeeDao);
        this.patientDao = new PatientDao();
        this.medicalRecordBuilder = new MedicalRecordBuilder(ioService, medicalEmployeeDao, patientDao);
        this.medicalRecordDao = new MedicalRecordDao();
    }

    public void addPatient(UserDetails userDetails) {
        Patient patient = patientBuilder.createPatient(userDetails);
        patientDao.save(patient);
    }

    public void displayAllPatients() {
        List<Patient> patientList = patientDao.findAll();
        ioService.display(patientList);
    }

    public void findPatientByLastName() {
        String lastName = ioService.getGenericPatientData("last name");
        List<Patient> patientList = patientDao.findByLastName(lastName);
        ioService.display(patientList);
    }

    public void displayPatients(UserDetails userDetails) {
        List<Patient> patientList = patientDao.findPatientsFor(userDetails.getMedicalEmployeeId());
        ioService.display(patientList);
    }

    public void addMedicalRecord(UserDetails userDetails) {
        MedicalRecord medicalRecord = medicalRecordBuilder.create(userDetails.getMedicalEmployeeId());
        medicalRecordDao.save(medicalRecord);
    }
}
