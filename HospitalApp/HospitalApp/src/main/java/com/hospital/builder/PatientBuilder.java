package com.hospital.builder;

import com.hospital.model.MedicalEmployee;
import com.hospital.model.Patient;
import com.hospital.service.IOService;
import com.hospital.util.UserDetails;
import dao.MedicalEmployeeDao;

public class PatientBuilder {
    private IOService ioService;
    private MedicalEmployeeDao medicalEmployeeDao;

    public PatientBuilder(IOService ioService, MedicalEmployeeDao medicalEmployeeDao) {
        this.ioService = ioService;
        this.medicalEmployeeDao = medicalEmployeeDao;
    }

    public Patient createPatient(UserDetails userDetails) {
        String firstName = ioService.getGenericPatientData("first name");
        String lastName = ioService.getGenericPatientData("last name");
        String email = ioService.getGenericPatientData("email");
        String cnp = ioService.getGenericPatientData("cnp");
        Patient patient = Patient.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .cnp(cnp)
                .build();
        linkPatientToMedic(userDetails, patient);
        return patient;
    }

    private void linkPatientToMedic(UserDetails userDetails, Patient patient) {
        MedicalEmployee medicalEmployee =
                medicalEmployeeDao.findById(MedicalEmployee.class, userDetails.getMedicalEmployeeId());
        medicalEmployee.addPatient(patient);
        patient.addMedic(medicalEmployee);
    }
}