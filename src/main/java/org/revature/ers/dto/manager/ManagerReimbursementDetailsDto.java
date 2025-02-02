package org.revature.ers.dto.manager;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ManagerReimbursementDetailsDto {
    private UUID reimbId;
    private String description;
    private double amount;
    private String status;
    private Date createdOn;
    private ManagerReimbursementEmployeeDetailsDto managerReimbursementEmployeeDetailsDto;
}
