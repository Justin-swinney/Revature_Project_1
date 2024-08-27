package org.revature.ers.service;

import org.revature.ers.dto.employee.ReimbursementCreationDto;
import org.revature.ers.dto.employee.EmployeeReimbursementsDto;
import org.revature.ers.exception.reimbursement.InvalidReimbursementAmountException;
import org.revature.ers.exception.user.UserIdNotFoundException;
import org.revature.ers.model.Reimbursement;
import org.revature.ers.model.User;
import org.revature.ers.repository.ReimbursementRepository;
import org.revature.ers.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


@Service
public class EmployeeService {

    private final UserRepository userRepository;
    private final ReimbursementRepository reimbursementRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeService(UserRepository userRepository, ReimbursementRepository reimbursementRepository) {
        this.userRepository = userRepository;
        this.reimbursementRepository = reimbursementRepository;
    }

    public ReimbursementCreationDto createReimbursement(ReimbursementCreationDto reimbursementCreationDto, UUID userId) {
        isValidUser(userId);
        if (reimbursementCreationDto.getAmount() < 0) {
            throw new InvalidReimbursementAmountException("Reimbursement amount cannot be negative.");
        }

        User user = userRepository.getReferenceById(userId);
        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setAmount(reimbursementCreationDto.getAmount());
        reimbursement.setDescription(reimbursementCreationDto.getDescription());
        reimbursementCreationDto.setStatus("PENDING");
        reimbursement.setUser(user);
        reimbursementRepository.save(reimbursement);
        return reimbursementCreationDto;
    }


    public EmployeeReimbursementsDto getEmployeeReimbursements(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserIdNotFoundException("User ID not found"));
        return new EmployeeReimbursementsDto(
                        user.getUserId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getReimbursements());
    }


    public List<Reimbursement> getAllReimbursementsByStatus(UUID userId, String status) {
        isValidUser(userId);
        User user = userRepository.getReferenceById(userId);
        //List<Reimbursement> filteredByStatusList = reimbursementRepository.findReimbursementByUserAndStatus(user, status);
        return reimbursementRepository.findReimbursementByUserAndStatus(user, status);

//        return filteredByStatusList.stream()
//                .map(reimbursement -> new EmployeeReimbursementsDto(
//                        reimbursement.getUser().getUserId(),
//                        reimbursement.getUser().getFirstName(),
//                        reimbursement.getUser().getLastName(),
//                        filteredByStatusList
//                )).collect(Collectors.toList());
    }

    public long getReimbursementCountByStatus(UUID userId, String status) {
        isValidUser(userId);
        return reimbursementRepository.countByUserAndStatus(userRepository.getReferenceById(userId), status);
    }

    public List<Reimbursement> getRecentReimbursements (UUID userId) {
        isValidUser(userId);
        User user = userRepository.getReferenceById(userId);
        return reimbursementRepository.findTop5ByUserOrderByCreatedOnDesc(user);
    }

    public void updateReimbursementDescription(UUID reimbId, String description) throws SQLException {
        if (reimbursementRepository.existsById(reimbId)) {
            Reimbursement reimbursement = reimbursementRepository.getReferenceById(reimbId);
            reimbursement.setDescription(description);
            reimbursementRepository.save(reimbursement);
        } else {
            throw new SQLException();
        }
    }

    private void isValidUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            logger.info("User Not Found with ID: {}", userId);
            throw new UserIdNotFoundException("User ID not found!");
        }
    }






    // TODO: [Some other functionality of your choice]

    // TODO: OPTIONAL: Update the description of a pending reimbursement
}
