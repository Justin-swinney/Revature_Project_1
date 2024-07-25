package org.revature.ers.service;

import org.revature.ers.dto.manager.ManagerEmployeesDetailsDto;
import org.revature.ers.dto.manager.ManagerReimbursementDetailsDto;
import org.revature.ers.dto.manager.ManagerReimbursementEmployeeDetailsDto;
import org.revature.ers.exception.CustomException;
import org.revature.ers.model.Reimbursement;
import org.revature.ers.model.User;
import org.revature.ers.repository.ReimbursementRepository;
import org.revature.ers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ManagerService {

    private final UserRepository userRepository;
    private final ReimbursementRepository reimbursementRepository;

    @Autowired
    public ManagerService(UserRepository userRepository, ReimbursementRepository reimbursementRepository) {
        this.userRepository = userRepository;
        this.reimbursementRepository = reimbursementRepository;
    }

    // TODO: CHECK MANAGER ROLES TO ENSURE ACCESS
    public List<ManagerEmployeesDetailsDto> getAllEmployees() {
        return userRepository.findAll().stream()
                .map(user -> new ManagerEmployeesDetailsDto(
                        user.getUserId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getRole()
                ))
                .collect(Collectors.toList());
    }

    // TODO: EXCEPTION HANDLING?
    public List<ManagerReimbursementDetailsDto> getAllReimbursement() {
        return reimbursementRepository.findAll().stream()
                .map(reimbursement -> new ManagerReimbursementDetailsDto(
                        reimbursement.getReimbId(),
                        reimbursement.getDescription(),
                        reimbursement.getAmount(),
                        reimbursement.getStatus(),
                        new ManagerReimbursementEmployeeDetailsDto(
                                reimbursement.getUser().getUserId(),
                                reimbursement.getUser().getFirstName(),
                                reimbursement.getUser().getLastName()
                        )
                )).collect(Collectors.toList());
    }

    // TODO: EXCEPTION HANDLING? AND POSSIBLE CHANGING CONTROLLER TO PATH PARAM ??
    public List<ManagerReimbursementDetailsDto> getAllReimbursementByStatus(String status) {
        return reimbursementRepository.findReimbursementByStatus(status.toUpperCase()).stream()
                .map(reimbursement -> new ManagerReimbursementDetailsDto(
                        reimbursement.getReimbId(),
                        reimbursement.getDescription(),
                        reimbursement.getAmount(),
                        reimbursement.getStatus(),
                        new ManagerReimbursementEmployeeDetailsDto(
                                reimbursement.getUser().getUserId(),
                                reimbursement.getUser().getFirstName(),
                                reimbursement.getUser().getLastName()
                        )
                )).collect(Collectors.toList());
    }

    // TODO: ADD MORE VALIDATION
   public void updateReimbursementStatus(UUID reimbId, String status) {
        if (reimbursementRepository.existsById(reimbId)) {
            Reimbursement reimbursement = reimbursementRepository.getReferenceById(reimbId);
            reimbursement.setStatus(status);
            reimbursementRepository.save(reimbursement);
        }
        else {
            throw new CustomException("test");
        }
    }

    // TODO: ADD MORE VALIDATION
    public void updateEmployeeRole(UUID userId, String role) {
        if (userRepository.existsById(userId)) {
            User user = userRepository.getReferenceById(userId);
            user.setRole(role);
            userRepository.save(user);
        }
        else {
            throw new CustomException("ERROR IN ROLE TEST TEMP");
        }
    }

    // TODO: ADD MORE VALIDATION
    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException("ID NOT FOUND TEMP PLACE HOLDER");
        }
         userRepository.deleteById(userId);
    }
}
