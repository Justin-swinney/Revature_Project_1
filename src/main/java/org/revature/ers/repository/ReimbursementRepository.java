package org.revature.ers.repository;

import org.revature.ers.model.Reimbursement;
import org.revature.ers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement, UUID> {

    List<Reimbursement> findReimbursementByStatus(String status);
    List<Reimbursement> findReimbursementByUserAndStatus(User user, String status);
}
