package org.revature.ers.controller;

import org.revature.ers.dto.ManagerEmployeesDetailsDto;
import org.revature.ers.dto.ManagerReimbursementDetailsDto;
import org.revature.ers.model.Reimbursement;
import org.revature.ers.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
