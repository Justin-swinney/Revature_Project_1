package org.revature.ers.controller;

import org.revature.ers.dto.manager.ManagerEmployeesDetailsDto;
import org.revature.ers.dto.manager.ManagerReimbursementDetailsDto;
import org.revature.ers.exception.CustomException;
import org.revature.ers.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }


    @GetMapping("/employees") //
    public ResponseEntity<?> getAllEmployees() {
        List<ManagerEmployeesDetailsDto> users = managerService.getAllEmployees();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/reimbursements")
    public ResponseEntity<?> getAllReimbursements() {
        List<ManagerReimbursementDetailsDto> reimbursements = managerService.getAllReimbursement();
        return ResponseEntity.ok().body(reimbursements);
    }

    // ****MAY REMOVE THIS ENDPOINT AND FILTER ON FRONT END****

    @GetMapping("/reimbursements/{status}")
    public ResponseEntity<?> getAllReimbursementsByStatus(@PathVariable String status) {
        List<ManagerReimbursementDetailsDto> reimbursements = managerService.getAllReimbursementByStatus(status);
        return ResponseEntity.ok().body(reimbursements);
    }

    @PatchMapping("reimbursement/{reimbId}")
    public ResponseEntity<?> updateReimbursementStatus(@PathVariable UUID reimbId, @RequestParam String status) {
        try {
            managerService.updateReimbursementStatus(reimbId, status);
        } catch (CustomException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.ok("TEST OK TEMP");
    }

    @DeleteMapping("/reimbursement/{userId}")
    public ResponseEntity<String> deleteReimbursements(@PathVariable UUID userId) {
        try {
            managerService.deleteUser(userId);
        } catch (CustomException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.ok().body("DELETED OK TEMP");
    }

    @PatchMapping("employees/{userId}/promote")
    public ResponseEntity<?> updateEmployeeRole(@PathVariable UUID userId, @RequestParam String role) {
        try {
            managerService.updateEmployeeRole(userId,role);
        } catch (CustomException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.ok().body("OK PROMOTED TEMP");
    }
}
