package org.revature.ers.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ManagerReimbursementEmployeeDetailsDto {
    private UUID userId;
    private String firstName;
    private String lastName;
}
