package org.revature.ers.controller;

import org.revature.ers.dto.employee.ReimbursementCreationDto;
import org.revature.ers.exception.CustomException;
import org.revature.ers.service.EmployeeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/{userId}/reimbursement/new")
    public ResponseEntity<?> createReimbursement(@RequestBody ReimbursementCreationDto reimbursementCreationDto, @PathVariable UUID userId) {
        try {
            ReimbursementCreationDto createdReimbursement = employeeService.createReimbursement(reimbursementCreationDto, userId);
            return ResponseEntity.ok(createdReimbursement);
        } catch (CustomException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @GetMapping("/{userId}/reimbursements")
    public ResponseEntity<?> getEmployeeReimbursements(@PathVariable UUID userId) {
        try {
            return ResponseEntity.ok(employeeService.getEmployeeReimbursements(userId));
        } catch (CustomException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{userId}/reimbursements/{status}")
    public ResponseEntity<?> getEmployeeReimbursementsByStatus(@PathVariable UUID userId, @PathVariable String status) {
        try {
            return ResponseEntity.ok(employeeService.getAllReimbursementsByStatus(userId, status));
        } catch (CustomException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}
