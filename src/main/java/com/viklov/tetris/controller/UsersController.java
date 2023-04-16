package com.viklov.tetris.controller;

import com.viklov.tetris.domain.JwtAuthentication;
import com.viklov.tetris.model.User;
import com.viklov.tetris.service.AuthService;
import com.viklov.tetris.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping(path = "register")
    public ResponseEntity<User> register(
            @RequestBody User user
    ) {
        User newUser = userService.createUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("hello/user")
    public ResponseEntity<String> helloUser() {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        return ResponseEntity.ok("Hello user " + authInfo.getPrincipal() + "!");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("hello/admin")
    public ResponseEntity<String> helloAdmin() {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        return ResponseEntity.ok("Hello admin " + authInfo.getPrincipal() + "!");
    }
}
