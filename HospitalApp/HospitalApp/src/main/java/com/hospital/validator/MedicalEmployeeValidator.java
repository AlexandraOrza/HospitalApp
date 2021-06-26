package com.hospital.validator;

import com.hospital.model.MedicalEmployee;
import com.hospital.service.IOService;
import dao.MedicalEmployeeDao;

import java.util.List;
import java.util.zip.InflaterOutputStream;

public class MedicalEmployeeValidator {
    private MedicalEmployeeDao medicalEmployeeDao;
    private IOService ioService;

    public MedicalEmployeeValidator(MedicalEmployeeDao medicalEmployeeDao, IOService ioService) {
        this.medicalEmployeeDao = medicalEmployeeDao;
        this.ioService = ioService;
    }

    public boolean isNotValid(MedicalEmployee medicalEmployee) {
        List<MedicalEmployee> medicalEmployeeList = medicalEmployeeDao.findByUsername(medicalEmployee.getUserName());
        if (medicalEmployeeList.size() > 0){
            ioService.displayError("Sorry, username already taken");
            return true;
        }
        return false;
    }
}
