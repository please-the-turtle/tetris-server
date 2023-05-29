package com.viklov.tetris.user;

import com.viklov.tetris.authentication.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @Size(min = 2, max = 20, message = "Username must contain between 2 and 20 characters.")
    @NotEmpty
    private String username;

    @NotEmpty
    @Size(min = 8, message = "Password must contain at least 8 chars.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[a-zA-Z]).*$",
            message = "Password must contain letters and numbers.")
    private String password;

    private Set<Role> roles;
}
