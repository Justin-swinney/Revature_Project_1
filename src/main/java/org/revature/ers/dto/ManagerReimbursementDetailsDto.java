package org.revature.ers.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private ManagerEmployeesDetailsDto managerEmployeesDetailsDto;
}
