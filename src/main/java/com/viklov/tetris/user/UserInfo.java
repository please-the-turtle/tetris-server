package com.viklov.tetris.user;

import com.viklov.tetris.authentication.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String username;
    private Set<Role> roles;
}
