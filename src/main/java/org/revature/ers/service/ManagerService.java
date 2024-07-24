package org.revature.ers.service;

import org.revature.ers.repository.ReimbursementRepository;
import org.revature.ers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    private final UserRepository userRepository;
    private final ReimbursementRepository reimbursementRepository;

    @Autowired
    public ManagerService(UserRepository userRepository, ReimbursementRepository reimbursementRepository) {
        this.userRepository = userRepository;
        this.reimbursementRepository = reimbursementRepository;
    }

    // TODO: See all reimbursements

    // TODO: See all pending reimbursements

    // TODO: Resolve a reimbursement (update status from PENDING to APPROVED or DENIED)

    // TODO: See all Users

    // TODO: Delete a User (should also delete any related reimbursements)

    // TODO: OPTIONAL: Update an employee’s role to manager
}
