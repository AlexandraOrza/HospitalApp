package com.hospital.builder;

import com.hospital.model.MedicalEmployee;
import com.hospital.service.IOService;
import com.hospital.util.MedicalType;

public class MedicalEmployeeBuilder {
    private IOService ioService;

    public MedicalEmployeeBuilder(IOService ioService) {
        this.ioService = ioService;
    }

    public MedicalEmployee createMedicalEmployee() {
        String userName = ioService.getRegistrationData("username");
        String password = ioService.getRegistrationData("password");
        String firstName = ioService.getRegistrationData("first name");
        String lastName = ioService.getRegistrationData("last name");
        String email = ioService.getRegistrationData("email");
        String medicalType = ioService.getMedicalStatus("medicalType");

        return MedicalEmployee.builder()
                .userName(userName)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .medicalType(convert(medicalType))
                .build();
    }

    private MedicalType convert(String medicalType) {
        if(medicalType.equalsIgnoreCase("DOCTOR")){
            return MedicalType.DOCTOR;
        } else if (medicalType.equalsIgnoreCase("NURSE")){
            return MedicalType.NURSE;
        }
        return MedicalType.OTHER;
    }
}
