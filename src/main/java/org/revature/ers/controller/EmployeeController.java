package org.revature.ers.controller;

import jakarta.servlet.http.HttpSession;
import org.revature.ers.dto.employee.ReimbursementCreationDto;
import org.revature.ers.exception.CustomException;
import org.revature.ers.model.Reimbursement;
import org.revature.ers.model.User;
import org.revature.ers.service.EmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin(origins="http://localhost:3000", allowCredentials = "true")
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/reimbursement/new")
    public ResponseEntity<?> createReimbursement(@RequestBody ReimbursementCreationDto reimbursementCreationDto, HttpSession httpSession) {
        try {
            UUID userId = getUserIdFromSession(httpSession);
            ReimbursementCreationDto createdReimbursement = employeeService.createReimbursement(reimbursementCreationDto, userId);
            System.out.println("EMPLOYEE createReimbursement HIT");
            return ResponseEntity.status(201).body(createdReimbursement);
        } catch (CustomException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/reimbursements")
    public ResponseEntity<?> getEmployeeReimbursements(HttpSession httpSession) {
        try {
           UUID userId = getUserIdFromSession(httpSession);
            System.out.println("EMPLOYEE getEmployeeReimbursements HIT");
            return ResponseEntity.ok(employeeService.getEmployeeReimbursements(userId));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/reimbursements/status")
    public ResponseEntity<?> getEmployeeReimbursementsByStatus(HttpSession httpSession, @RequestHeader String status) {
        try {
            status = status.toUpperCase();
            UUID userId = getUserIdFromSession(httpSession);
            System.out.println("EMPLOYEE getEmployeeReimbursementsByStatus HIT");
            return ResponseEntity.ok(employeeService.getAllReimbursementsByStatus(userId, status));
        } catch (CustomException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/reimbursements/status/count")
    public ResponseEntity<Long> getReimbursementCountByStatus(HttpSession httpSession, @RequestParam String status) {
            UUID userId = getUserIdFromSession(httpSession);
            System.out.println("EMPLOYEE getReimbursementCountByStatus HIT");
            return ResponseEntity.ok(employeeService.getReimbursementCountByStatus(userId, status));
        }

    @GetMapping("/reimbursements/recent")
    public ResponseEntity<List<Reimbursement>> getRecentReimbursements(HttpSession httpSession) {
        UUID userId = getUserIdFromSession(httpSession);
        System.out.println("EMPLOYEE getRecentReimbursements HIT");
        return ResponseEntity.ok(employeeService.getRecentReimbursements(userId));
    }

    @PatchMapping("/reimbursement/update")
    public ResponseEntity<?> updateReimbursementStatus(@RequestHeader("reimbId") UUID reimbID, @RequestHeader("description") String description) {
        try {
            employeeService.updateReimbursementDescription(reimbID ,description);
            System.out.println("EMPLOYEE updateReimbursementStatus HIT");
            return ResponseEntity.ok("Description updated successfully");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/checkRole")
    public ResponseEntity<Object> checkRole(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("USER");
        System.out.println("EMPLOYEE checkRole HIT");
        return ResponseEntity.ok(user.getRole());
    }

    private UUID getUserIdFromSession(HttpSession httpSession) {
        return (UUID) httpSession.getAttribute("userId");
    }


}
