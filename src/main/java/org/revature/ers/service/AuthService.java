package org.revature.ers.service;

import org.revature.ers.dto.auth.LoginDto;
import org.revature.ers.dto.auth.RegistrationDto;
import org.revature.ers.exception.CustomException;
import org.revature.ers.exception.user.UsernameAlreadyExistException;
import org.revature.ers.model.User;
import org.revature.ers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public String registerUser(RegistrationDto registrationDto) {
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new UsernameAlreadyExistException("Username Already Exist!");
        }
            User user = new User();
            user.setFirstName(registrationDto.getFirstName());
            user.setLastName(registrationDto.getLastName());
            user.setUsername(registrationDto.getUsername());
            user.setPassword(registrationDto.getPassword());
            userRepository.save(user);
            return registrationDto.getUsername();
    }

    public void loginUser(LoginDto loginDto) throws CustomException {
    }


    // TODO: Login

    // TODO: Logout

}
