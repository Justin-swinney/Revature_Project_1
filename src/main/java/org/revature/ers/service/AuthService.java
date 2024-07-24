package org.revature.ers.service;

import org.revature.ers.dto.UserRegistrationDto;
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

    public String  registerUser(UserRegistrationDto userRegistrationDto) {
        if (userRepository.existsByUsername(userRegistrationDto.getUsername())) {
            throw new UsernameAlreadyExistException("Username Already Exist!");
        }
            User user = new User();
            user.setFirstName(userRegistrationDto.getFirstName());
            user.setLastName(userRegistrationDto.getLastName());
            user.setUsername(userRegistrationDto.getUsername());
            user.setPassword(userRegistrationDto.getPassword());
            userRepository.save(user);
            return userRegistrationDto.getUsername();
    }

    // TODO: Login

    // TODO: Logout

}
