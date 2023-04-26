package com.viklov.tetris.registration;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationData {

    @NotEmpty
    @Size(min = 2, max = 20, message = "Username must contain between 2 and 20 characters.")
    private String username;

    @NotEmpty
    @Size(min = 8, message = "Password must contain at least 8 chars.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[a-zA-Z]).*$",
            message = "Password must contain letters and numbers.")
    private String password;
}
