package org.revature.ers.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReimbursementCreationDto {
    private String description;
    private double amount;
    private String status;
}
