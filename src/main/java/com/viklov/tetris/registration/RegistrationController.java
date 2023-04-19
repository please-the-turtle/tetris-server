package com.viklov.tetris.registration;

import com.viklov.tetris.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping()
    public ResponseEntity<String> register(
            @RequestBody RegistrationData registrationData
    ) {
        User newUser = registrationService.registerUser(registrationData);
        String responseMessage = String.format("User with username %s created.", newUser.getUsername());

        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }
}
