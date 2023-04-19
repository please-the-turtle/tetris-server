package com.viklov.tetris.user;

import com.viklov.tetris.authentication.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username", unique = true)
    @Size(min = 2, max = 20, message = "Username must contain between 2 and 20 characters.")
    @NotEmpty
    private String username;

    @Column(name = "password")
    @Size(min = 8, message = "Password must contain at least 8 characters.")
    @NotEmpty
    private String password;

    @ElementCollection(targetClass = Role.class)
    @CollectionTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
