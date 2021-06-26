package com.hospital.service;

import com.hospital.model.Patient;
import com.hospital.util.UserDetails;

import java.util.List;
import java.util.Scanner;

public class IOService {
    private Scanner scanner;

    public IOService() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu(UserDetails userDetails) {
        if (userDetails.isLoggedIn()) {
            displayLoggedInUserMenu(userDetails);
        } else {
            displayGuestUserMenu();
        }
    }

    private void displayGuestUserMenu() {
        System.out.println("Welcome stranger, please select from the following options:");
        System.out.println("[1] - Login");
        System.out.println("[2] - Register");
        System.out.println("[0] - Exit");
    }

    private void displayLoggedInUserMenu(UserDetails userDetails) {
        System.out.println("Welcome " + userDetails.getFriendlyName() + ", please select one of the following options:");
        System.out.println("[1] - Add patient");
        System.out.println("[2] - View all patients");
        System.out.println("[3] - View my patients");
        System.out.println("[4] - Find patient by name");
        System.out.println("[5] - Add medical records");
        System.out.println("[0] - Logout");
    }

    public int getUserInput() {
        String input = scanner.nextLine();
        return Integer.parseInt(input);
    }

    public String getGenericPatientData(String info) {
        System.out.println("Insert patient's " + info + ": ");
        return scanner.nextLine();
    }

    public String getRegistrationData(String info) {
        System.out.println("Insert your " + info + ": ");
        return scanner.nextLine();
    }

    public void display(List<Patient> patientList) {
        if (patientList.size() == 0) {      //GOD's claws
            System.out.println("Sorry, no patient found");
            return;
        }
        System.out.println("We have the following patients: ");
        for (Patient element : patientList) {
            System.out.println(element.getFirstName() + " " + element.getLastName());
        }
    }

    public String getMedicalStatus(String medicalType) {
        System.out.print("Are you a doctor or a nurse? Please insert your title: ");
        return scanner.nextLine();
    }


    public String getUserCredentials(String info) {
        System.out.println("Insert your " + info + ": ");
        return scanner.nextLine();
    }

    public void displayError(String message) {
        display(message);
    }

    public void displaySuccessfullMessage(String message) {
        display(message);
    }

    private void display(String message) {
        System.out.println(message);
    }
}
