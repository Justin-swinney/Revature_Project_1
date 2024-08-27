package org.revature.ers.dto.auth;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginResponse {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String role;
    private String username;
}
