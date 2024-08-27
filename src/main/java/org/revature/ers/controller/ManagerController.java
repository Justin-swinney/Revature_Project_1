package org.revature.ers.controller;

import org.revature.ers.dto.manager.ManagerEmployeesDetailsDto;
import org.revature.ers.dto.manager.ManagerReimbursementDetailsDto;
import org.revature.ers.exception.CustomException;
import org.revature.ers.service.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin(origins="http://localhost:3000", allowCredentials = "true")
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
        System.out.println(" MANAGER getAllReimbursements HIT");
        return ResponseEntity.ok().body(reimbursements);
    }

    // ****MAY REMOVE THIS ENDPOINT AND FILTER ON FRONT END****
    @GetMapping("/reimbursements/status")
    public ResponseEntity<?> getAllReimbursementsByStatus(@RequestHeader String status) {
        status = status.toUpperCase();
        List<ManagerReimbursementDetailsDto> reimbursements = managerService.getAllReimbursementByStatus(status);
        System.out.println(" MANAGER getAllReimbursementsByStatus HIT");
        return ResponseEntity.ok().body(reimbursements);
    }

    @PatchMapping("/reimbursement/update-status")
    public ResponseEntity<?> updateReimbursementStatus(
            @RequestHeader("reimbId") UUID reimbId,
            @RequestHeader("status") String status) {
        try {
            status = status.toUpperCase();
            managerService.updateReimbursementStatus(reimbId, status);
            System.out.println(" MANAGER updateReimbursementStatus HIT");
            return ResponseEntity.ok("Status updated successfully");
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/employee/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable UUID userId) {
        try {
            managerService.deleteUser(userId);
        } catch (CustomException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        System.out.println(" MANAGER deleteUserById HIT");
        return ResponseEntity.ok().body("Deleted User Successfully");
    }

    @PatchMapping("employees/promote")
    public ResponseEntity<?> updateEmployeeRole(@RequestHeader String userId,@RequestHeader String role) {
        try {
            UUID id = UUID.fromString(userId);
            managerService.updateEmployeeRole(id,role.toUpperCase());
        } catch (CustomException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        System.out.println(" MANAGER updateEmployeeRole HIT");
        return ResponseEntity.status(200).body("Promoted user successfully");
    }

    @GetMapping("/reimbursements/status/count")
    public ResponseEntity<Long> getReimbursementCountByStatus(@RequestParam String status) {
        System.out.println(" MANAGER getReimbursementCountByStatus HIT");
        return ResponseEntity.ok(managerService.getAllReimbursementCountByStatus(status));
    }
}
