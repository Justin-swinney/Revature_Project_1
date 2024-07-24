package org.revature.ers.controller;

import org.revature.ers.dto.ReimbursementCreationDto;
import org.revature.ers.dto.EmployeeReimbursementsDto;
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

    @PostMapping("/{employeeId}/reimbursement/new")
    public ResponseEntity<?> createReimbursement(@RequestBody ReimbursementCreationDto reimbursementCreationDto, @PathVariable UUID employeeId) {
        try {
            ReimbursementCreationDto createdReimbursement = employeeService.createReimbursement(reimbursementCreationDto, employeeId);
            return ResponseEntity.ok(createdReimbursement);
        } catch (CustomException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @GetMapping("/{employeeId}/reimbursements")
    public ResponseEntity<?> getEmployeeReimbursements(@PathVariable UUID employeeId) {
        try {
            return ResponseEntity.ok(employeeService.getEmployeeReimbursements(employeeId));
        } catch (CustomException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}
