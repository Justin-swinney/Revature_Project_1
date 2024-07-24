package org.revature.ers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.revature.ers.model.Reimbursement;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeReimbursementsDto {
    private UUID userId;
    private String firstName;
    private String lastName;
    private List<Reimbursement> reimbursements;
}
