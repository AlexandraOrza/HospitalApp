package com.hospital.service;

import com.hospital.builder.MedicalEmployeeBuilder;
import com.hospital.model.MedicalEmployee;
import com.hospital.util.MedicalType;
import com.hospital.util.UserDetails;
import com.hospital.validator.MedicalEmployeeValidator;
import dao.MedicalEmployeeDao;

import java.util.List;


public class MedicalEmployeeService {
    private MedicalEmployeeBuilder medicalEmployeeBuilder;
    private MedicalEmployeeDao medicalEmployeeDao;
    private IOService ioService;
    private MedicalEmployeeValidator medicalEmployeeValidator;

    public MedicalEmployeeService(IOService ioService, MedicalEmployeeDao medicalEmployeeDao){
        this.medicalEmployeeBuilder = new MedicalEmployeeBuilder(ioService);
        this.medicalEmployeeDao = medicalEmployeeDao;
        this.ioService = ioService;
        this.medicalEmployeeValidator = new MedicalEmployeeValidator(medicalEmployeeDao, ioService);
    }

    public void register() {
        MedicalEmployee medicalEmployee = medicalEmployeeBuilder.createMedicalEmployee();
        //vom adauga validare pt username
        if(medicalEmployeeValidator.isNotValid(medicalEmployee)){
            return;
        }
        medicalEmployeeDao.save(medicalEmployee);
    }

    public void login(UserDetails userDetails) {
        String username = ioService.getUserCredentials("username");
        String password = ioService.getUserCredentials("password");

        List<MedicalEmployee> userList = medicalEmployeeDao.findByUsername(username);
        if(userList.isEmpty()){         // acelasi lucru cu (size() == 0)
            ioService.displayError("Username not found, please register first.");
            return;
        } else if(userList.size() > 1){
            ioService.displayError("User invalid please contact administrator");
            return;
        }

        MedicalEmployee user = userList.get(0);

        if(!user.getPassword().equals(password)){
            ioService.displayError("incorrect password, please try again");
            return;
        }
        ioService.displaySuccessfullMessage("Login successful");
        populateUserDetails(userDetails, user);
    }

    private void populateUserDetails(UserDetails userDetails, MedicalEmployee user) {
        userDetails.setLoggedIn(true);
        userDetails.setFriendlyName(user.getFirstName());
        if(user.getMedicalType() == MedicalType.DOCTOR) {
            userDetails.setDoctor(true);
        }
        userDetails.setMedicalEmployeeId(user.getMedicalEmployeeId());
    }

    public void logout(UserDetails userDetails) {
        ioService.displaySuccessfullMessage("You have been successfully logged out");
        userDetails.setLoggedIn(false);
        userDetails.setHasJustBeenLoggedOut(true);
    }
}
