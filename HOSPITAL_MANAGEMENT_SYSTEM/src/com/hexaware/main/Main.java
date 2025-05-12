package com.hexaware.main;

import com.hexaware.dao.IHospitalServiceImpl;
import com.hexaware.model.Appointment;
import java.sql.Date; 
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IHospitalServiceImpl service = new IHospitalServiceImpl();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("Hospital Management System");
            System.out.println("1. Schedule Appointment");
            System.out.println("2. Get Appointment by ID");
            System.out.println("3. Get Appointments for Patient");
            System.out.println("4. Get Appointments for Doctor");
            System.out.println("5. Get Appointments for Doctor on a Specific Date"); // New option
            System.out.println("6. Update Appointment");
            System.out.println("7. Cancel Appointment");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            // Input validation for choice
            while (true) {
                try {
                    choice = scanner.nextInt();
                    break; // Exit the loop if input is valid
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 8.");
                    scanner.next(); // Clear the invalid input
                }
            }

            switch (choice) {
                case 1: // Schedule Appointment
                    System.out.print("Enter Appointment ID: ");
                    int appointmentId = scanner.nextInt();
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    System.out.print("Enter Patient First Name: ");
                    String patientFirstName = scanner.next();
                    System.out.print("Enter Patient Last Name: ");
                    String patientLastName = scanner.next();
                    System.out.print("Enter Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    System.out.print("Enter Doctor First Name: ");
                    String doctorFirstName = scanner.next();
                    System.out.print("Enter Doctor Last Name: ");
                    String doctorLastName = scanner.next();
                    System.out.print("Enter Appointment Date (yyyy-mm-dd): ");
                    String date = scanner.next();
                    System.out.print("Enter Description: ");
                    String description = scanner.next();

                    try {
                        Appointment appointment = new Appointment(
                            appointmentId, patientId, patientFirstName, patientLastName, 
                            doctorId, doctorFirstName, doctorLastName, Date.valueOf(date), description
                        );
                        service.scheduleAppointment(appointment);
                        System.out.println("Appointment Scheduled!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid date format. Please use yyyy-mm-dd.");
                    }
                    break;

                case 2: // Get Appointment by ID
                    System.out.print("Enter Appointment ID: ");
                    Appointment app = service.getAppointmentById(scanner.nextInt());
                    System.out.println(app != null ? app : "Appointment not found.");
                    break;

                case 3: // Get Appointments for Patient
                    System.out.print("Enter Patient ID: ");
                    List<Appointment> patientAppointments = service.getAppointmentsForPatient(scanner.nextInt());
                    System.out.println("Appointments for Patient: ");
                    for (Appointment patientApp : patientAppointments) {
                        System.out.println(patientApp);
                    }
                    break;

                case 4: // Get Appointments for Doctor
                    System.out.print("Enter Doctor ID: ");
                    List<Appointment> doctorAppointments = service.getAppointmentsForDoctor(scanner.nextInt());
                    System.out.println("Appointments for Doctor: ");
                    for (Appointment doctorApp : doctorAppointments) {
                        System.out.println(doctorApp);
                    }
                    break;

                case 5: // Get Appointments for Doctor on a Specific Date
                    System.out.print("Enter Doctor ID: ");
                    int docId = scanner.nextInt();
                    System.out.print("Enter Appointment Date (yyyy-mm-dd): ");
                    String specificDate = scanner.next();
                    try {
                        Date appointmentDate = Date.valueOf(specificDate);
                        List<Appointment> doctorAppointmentsOnDate = service.getAppointmentsForDoctorOnDate(docId, appointmentDate);
                        System.out.println("Appointments for Doctor on " + specificDate + ": ");
                        for (Appointment doctorAppOnDate : doctorAppointmentsOnDate) {
                            System.out.println(doctorAppOnDate);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid date format. Please use yyyy-mm-dd.");
                    }
                    break;

                case 6: // Update Appointment
                    System.out.print("Enter Appointment ID to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new Patient ID: ");
                    int newPatientId = scanner.nextInt();
                    System.out.print("Enter new Patient First Name: ");
                    String newPatientFirstName = scanner.next();
                    System.out.print("Enter new Patient Last Name: ");
                    String newPatientLastName = scanner.next();
                    System.out.print("Enter new Doctor ID: ");
                    int newDoctorId = scanner.nextInt();
                    System.out.print("Enter new Doctor First Name: ");
                    String newDoctorFirstName = scanner.next();
                    System.out.print("Enter new Doctor Last Name: ");
                    String newDoctorLastName = scanner.next();
                    System.out.print("Enter new Appointment Date (yyyy-mm-dd): ");
                    String newDate = scanner.next();
                    System.out.print("Enter new Description: ");
                    String newDescription = scanner.next();

                    try {
                        Appointment updatedAppointment = new Appointment(
                            updateId, newPatientId, newPatientFirstName, newPatientLastName, 
                            newDoctorId, newDoctorFirstName, newDoctorLastName, Date.valueOf(newDate), newDescription
                        );
                        if (service.updateAppointment(updatedAppointment)) {
                            System.out.println("Appointment Updated!");
                        } else {
                            System.out.println("Appointment not found for update.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid date format. Please use yyyy-mm-dd.");
                    }
                    break;

                case 7: // Cancel Appointment
                    System.out.print("Enter Appointment ID to cancel: ");
                    if (service.cancelAppointment(scanner.nextInt())) {
                        System.out.println("Appointment Cancelled!");
                    } else {
                        System.out.println("Appointment not found for cancellation.");
                    }
                    break;

                case 8: // Exit
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);

        scanner.close();
    }
}
