/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao;

/**
 *
 * @author User
 */

import com.mycompany.api.model.MedicalRecord;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {
    // List to store mock medical record data
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();

    // Add some mock data
    static {
        medicalRecords.add(new MedicalRecord(1, "John Doe", "Heart condition", "High blood pressure"));
        medicalRecords.add(new MedicalRecord(2, "Jane Smith", "Asthma", "Allergy to penicillin"));
    }

    // Method to get all medical records
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecords;
    }

    // Method to get a medical record by ID
    public MedicalRecord getMedicalRecordById(int id) {
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getId() == id) {
                return medicalRecord;
            }
        }
        return null; // Return null if medical record not found
    }

    // Method to add a new medical record
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
    }

    // Method to update an existing medical record
    public void updateMedicalRecord(int id, MedicalRecord updatedMedicalRecord) {
        for (int i = 0; i < medicalRecords.size(); i++) {
            if (medicalRecords.get(i).getId() == id) {
                updatedMedicalRecord.setId(id); // Set the ID of the updated medical record
                medicalRecords.set(i, updatedMedicalRecord);
                return; // Exit loop once medical record is updated
            }
        }
    }

    // Method to delete a medical record by ID
    public void deleteMedicalRecord(int id) {
        medicalRecords.removeIf(medicalRecord -> medicalRecord.getId() == id);
    }
}


