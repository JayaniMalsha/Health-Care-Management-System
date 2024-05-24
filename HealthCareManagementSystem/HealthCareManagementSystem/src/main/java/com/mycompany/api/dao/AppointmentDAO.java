/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao;

/**
 *
 * @author User
 */

import com.mycompany.api.model.Appointment;
import com.mycompany.api.model.Doctor;
import com.mycompany.api.model.Patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentDAO {
    // List to store mock appointment data
    private static List<Appointment> appointments = new ArrayList<>();

    // Add some mock data
    static {
        Patient patient1 = new Patient(1, "John Doe", "john@example.com", "123 Main St", "Allergic to peanuts", "Stable");
        Doctor doctor1 = new Doctor(1, "Dr. Smith", "smith@example.com", "789 Elm St", "Cardiologist");
        appointments.add(new Appointment(1, new Date(), "10:00 AM", patient1, doctor1));

        Patient patient2 = new Patient(2, "Jane Smith", "jane@example.com", "456 Oak St", "No major health issues", "Good");
        Doctor doctor2 = new Doctor(2, "Dr. Johnson", "johnson@example.com", "101 Pine St", "Pediatrician");
        appointments.add(new Appointment(2, new Date(), "11:00 AM", patient2, doctor2));
    }

    // Method to get all appointments
    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    // Method to get an appointment by ID
    public Appointment getAppointmentById(int id) {
        for (Appointment appointment : appointments) {
            if (appointment.getId() == id) {
                return appointment;
            }
        }
        return null; // Return null if appointment not found
    }

    // Method to add a new appointment
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    // Method to update an existing appointment
    public void updateAppointment(int id, Appointment updatedAppointment) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getId() == id) {
                updatedAppointment.setId(id); // Set the ID of the updated appointment
                appointments.set(i, updatedAppointment);
                return; // Exit loop once appointment is updated
            }
        }
    }

    // Method to delete an appointment by ID
    public void deleteAppointment(int id) {
        appointments.removeIf(appointment -> appointment.getId() == id);
    }

    // Method to schedule a new appointment
    public void scheduleAppointment(Appointment appointment) {
        // Add implementation to schedule appointment
        // You can set additional properties of the appointment here
        // For example, you can set a status like "scheduled"
    }

    // Method to cancel an appointment by ID
    public void cancelAppointment(int id) {
        // Add implementation to cancel appointment
        // You can remove the appointment from the list or mark it as cancelled
    }
}



