package org.revature.ers.dto.employee;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReimbursementCreationDto {

    @NotBlank(message = "Please provide a description")
    private String description;

    @NotBlank(message = "Please prove a amount")
    private double amount;
    
    private String status;
}
