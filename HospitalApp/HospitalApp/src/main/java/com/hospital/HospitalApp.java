package com.hospital;

import com.hospital.service.IOService;
import com.hospital.service.MedicalEmployeeService;
import com.hospital.service.PatientService;
import com.hospital.util.UserDetails;
import dao.MedicalEmployeeDao;

public class HospitalApp {

    private IOService ioService;
    private PatientService patientService;
    private MedicalEmployeeService medicalEmployeeService;
    private MedicalEmployeeDao medicalEmployeeDao;

    public HospitalApp() {
        this.ioService = new IOService();
        this.medicalEmployeeDao = new MedicalEmployeeDao();
        this.patientService = new PatientService(this.ioService, medicalEmployeeDao);
        this.medicalEmployeeService = new MedicalEmployeeService(this.ioService, medicalEmployeeDao);
    }

    public void start() {
        int userInput;
        UserDetails userDetails = new UserDetails();
        do {
                //Pasul 1 - afisam un meniu
            ioService.displayMenu(userDetails);
                //Pasul 2 - luam input de la user
            userInput = ioService.getUserInput();
                //Pasul 3 - procesam inputul user-ului
            process(userInput, userDetails);
                //Pasul 4 - Goto step 1
        } while (isNotExitCondition(userInput, userDetails));
    }

    private boolean isNotExitCondition(int userInput, UserDetails userDetails) {
        return (userInput != 0 || userDetails.isHasJustBeenLoggedOut());
    }

    private void process(int userInput, UserDetails userDetails) {
        if (userDetails.isLoggedIn()) {
            processInputLoggedIn(userInput, userDetails);
        } else {
            processInputGuest(userInput, userDetails);
        }
    }

    private void processInputGuest(int userInput, UserDetails userDetails) {
        userDetails.setHasJustBeenLoggedOut(false); /////////////////
        switch (userInput) {
            case 1: {
                medicalEmployeeService.login(userDetails);
                break;
            }
            case 2: {
                medicalEmployeeService.register();
                break;
            }
        }
    }

    private void processInputLoggedIn(int userInput, UserDetails userDetails) {

        switch (userInput) {
            case 1: {
                patientService.addPatient(userDetails);
                break;
            }
            case 2: {
                patientService.displayAllPatients();
                break;
            }
            case 3:{
                patientService.displayPatients(userDetails);
                break;
            }
            case 4: {
                patientService.findPatientByLastName();
                break;
            }
            case 5:{
                patientService.addMedicalRecord(userDetails);
                break;
            }
            case 0: {
                medicalEmployeeService.logout(userDetails);
                break;
            }
        }
    }


}
