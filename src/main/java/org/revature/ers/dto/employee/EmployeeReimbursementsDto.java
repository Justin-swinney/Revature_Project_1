package org.revature.ers.dto.employee;

import lombok.*;
import org.revature.ers.model.Reimbursement;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EmployeeReimbursementsDto {
    private UUID userId;
    private String firstName;
    private String lastName;
    private List<Reimbursement> reimbursements;
}
