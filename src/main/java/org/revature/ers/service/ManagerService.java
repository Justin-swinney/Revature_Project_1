package org.revature.ers.service;

import org.revature.ers.dto.ManagerEmployeesDetailsDto;
import org.revature.ers.dto.ManagerReimbursementDetailsDto;
import org.revature.ers.model.Reimbursement;
import org.revature.ers.repository.ReimbursementRepository;
import org.revature.ers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
                        user.getLastName()
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
                        new ManagerEmployeesDetailsDto(
                                reimbursement.getUser().getUserId(),
                                reimbursement.getUser().getFirstName(),
                                reimbursement.getUser().getLastName()
                        )
                )).collect(Collectors.toList());
    }



    // TODO: See all pending reimbursements

    // TODO: Resolve a reimbursement (update status from PENDING to APPROVED or DENIED)


    // TODO: Delete a User (should also delete any related reimbursements)

    // TODO: OPTIONAL: Update an employee’s role to manager
}
