package org.revature.ers.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
