package org.revature.ers.dto.auth;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistrationDto {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
