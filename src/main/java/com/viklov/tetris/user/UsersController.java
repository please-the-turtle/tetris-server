package com.viklov.tetris.user;

import com.viklov.tetris.authentication.jwt.JwtAuthentication;
import com.viklov.tetris.authentication.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UsersController {

    private final AuthService authService;
    private final UserService userService;

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

    @GetMapping()
    public ResponseEntity<UserInfo> getUserInfo(@RequestParam String username) {
        User user = userService.loadUserByUsername(username);
        UserInfo userInfo = new UserInfo(
                user.getUsername(),
                user.getRoles()
        );

        return ResponseEntity.ok(userInfo);
    }
}
