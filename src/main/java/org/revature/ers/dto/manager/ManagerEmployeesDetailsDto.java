package org.revature.ers.dto.manager;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ManagerEmployeesDetailsDto {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String role;
}
