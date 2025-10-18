package com.example.patientservice.controller;

import com.example.patientservice.entity.Patient;
import com.example.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "https://demo.devopscicd.xyz", allowCredentials = "true")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getPatients() {
        List<Patient> patients = patientService.getAllPatients();
        patients.forEach(patient -> patient.setPassword(null));
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        if (patient.isPresent()) {
            Patient u = patient.get();
            u.setPassword(null);
            return ResponseEntity.ok(u);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Patient patient) {
        if (patient.getEmail() == null || patient.getPassword() == null || patient.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Email and password must not be empty.");
        }

        if (patientService.getPatientByEmail(patient.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered.");
        }

        Patient created = patientService.createPatient(patient);
        created.setPassword(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Patient loginPatient) {
        Optional<Patient> patientOptional = patientService.getPatientByEmail(loginPatient.getEmail());

        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            boolean matches = patientService.verifyPassword(loginPatient.getPassword(), patient.getPassword());

            if (matches) {
                patient.setPassword(null);

                Map<String, Object> response = new HashMap<>();
                response.put("token", "dummy-token"); // Replace with JWT later
                response.put("patient", patient);

                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Patient updated = patientService.updatePatient(id, patient);
        if (updated != null) {
            updated.setPassword(null);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        if (patientService.getPatientById(id).isPresent()) {
            patientService.deletePatient(id);
            return ResponseEntity.ok("Patient deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
    }
}
