package org.revature.ers.service;

import org.revature.ers.dto.employee.ReimbursementCreationDto;
import org.revature.ers.dto.employee.EmployeeReimbursementsDto;
import org.revature.ers.exception.user.UserIdNotFoundException;
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
public class EmployeeService {

    private final UserRepository userRepository;
    private final ReimbursementRepository reimbursementRepository;

    @Autowired
    public EmployeeService(UserRepository userRepository, ReimbursementRepository reimbursementRepository) {
        this.userRepository = userRepository;
        this.reimbursementRepository = reimbursementRepository;
    }

    // TODO: ADD MORE VALIDATION
    public ReimbursementCreationDto createReimbursement(ReimbursementCreationDto reimbursementCreationDto, UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserIdNotFoundException("User ID not found!");
        }
        User user = userRepository.getReferenceById(userId);
        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setAmount(reimbursementCreationDto.getAmount());
        reimbursement.setDescription(reimbursementCreationDto.getDescription());
        reimbursement.setStatus(reimbursementCreationDto.getStatus());
        reimbursement.setUser(user);
        reimbursementRepository.save(reimbursement);
        return reimbursementCreationDto;
    }

    // TODO: ADD MORE VALIDATION
    public EmployeeReimbursementsDto getEmployeeReimbursements(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserIdNotFoundException("User ID not found!");
        }
        return userRepository.findById(userId)
                .map(user -> new EmployeeReimbursementsDto(
                        user.getUserId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getReimbursements()))
                .orElseThrow(() -> new UserIdNotFoundException("User ID not found!"));
    }

    // TODO: ADD MORE VALIDATION
    public List<EmployeeReimbursementsDto> getAllReimbursementsByStatus(UUID userId, String status) {
        if (!userRepository.existsById(userId)) {
            throw new UserIdNotFoundException("User ID not found!");
        }
        User user = userRepository.getReferenceById(userId);
        return reimbursementRepository.findReimbursementByUserAndStatus(user, status).stream()
                .map(reimbursement -> new EmployeeReimbursementsDto(
                        reimbursement.getReimbId(),
                        reimbursement.getUser().getFirstName(),
                        reimbursement.getUser().getLastName(),
                        reimbursement.getUser().getReimbursements()
                )).collect(Collectors.toList());
    }






    // TODO: [Some other functionality of your choice]

    // TODO: OPTIONAL: Update the description of a pending reimbursement
}
