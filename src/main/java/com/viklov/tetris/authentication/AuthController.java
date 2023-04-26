package com.viklov.tetris.authentication;

import com.viklov.tetris.authentication.jwt.JwtRequest;
import com.viklov.tetris.authentication.jwt.JwtResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;
    private final long jwtRefreshExpiration;

    public AuthController(AuthService authService,
                          @Value("${jwt.secret.refreshExpirationDays}") long jwtRefreshExpiration) {
        this.authService = authService;
        this.jwtRefreshExpiration = jwtRefreshExpiration;
    }

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(
            @RequestBody JwtRequest authRequest
    ) {
        final JwtResponse token = authService.login(authRequest);
        ResponseCookie refreshTokenCookie = createRefreshTokenCookie(token.getRefreshToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(token);
    }

    @PostMapping("logout")
    public ResponseEntity<Void> logout(@CookieValue(name = "refreshToken") String refreshToken) {
        if (refreshToken != null) {
            authService.logout(refreshToken);
        }

        ResponseCookie clearRefreshTokenCookie = ResponseCookie.from("refreshToken")
                .maxAge(0)
                .httpOnly(true)
                .value(null)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, clearRefreshTokenCookie.toString())
                .build();
    }

    @GetMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@CookieValue(name = "refreshToken") String refreshToken) {
        final JwtResponse token = authService.getAccessToken(refreshToken);
        return ResponseEntity.ok(token);
    }

    @GetMapping("refresh")
    public ResponseEntity<Void> getNewRefreshToken(@CookieValue(name = "refreshToken") String refreshToken) {
        final JwtResponse token = authService.refresh(refreshToken);
        ResponseCookie refreshTokenCookie = createRefreshTokenCookie(token.getRefreshToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .build();
    }

    private ResponseCookie createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refreshToken")
                .value(refreshToken)
                .httpOnly(true)
                .maxAge(TimeUnit.DAYS.toSeconds(jwtRefreshExpiration))
                .secure(true)
                .sameSite("Lax")
                .build();
    }
}
