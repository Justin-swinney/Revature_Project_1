package org.revature.ers.service;

import jakarta.transaction.Transactional;
import org.revature.ers.dto.auth.LoginDto;
import org.revature.ers.dto.auth.LoginResponse;
import org.revature.ers.dto.auth.RegistrationDto;
import org.revature.ers.exception.CustomException;
import org.revature.ers.exception.user.InvalidUsernamePasswordException;
import org.revature.ers.exception.user.UsernameAlreadyExistException;
import org.revature.ers.model.User;
import org.revature.ers.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public String registerUser(RegistrationDto registrationDto) {
        validateUsername(registrationDto.getUsername());
        User user = buildUserFrontDto(registrationDto);
        userRepository.save(user);
        logger.info("User registered successfully : {}", registrationDto.getUsername());
        return registrationDto.getUsername();
    }

    public LoginResponse loginUser(LoginDto loginDto) throws CustomException {
        User user = userRepository.findByUsername(loginDto.getUsername());
        if (user != null && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return new LoginResponse(user.getUserId(), user.getFirstName(), user.getLastName(), user.getRole(), user.getUsername());
        }
        throw new InvalidUsernamePasswordException("Invalid Username or Password");
    }

    private void validateUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistException("Username Already Exist!");
        }
    }

    private User buildUserFrontDto(RegistrationDto registrationDto) {
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        return user;
    }
}
