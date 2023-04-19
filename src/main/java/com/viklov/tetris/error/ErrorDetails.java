package com.viklov.tetris.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    @Getter
    @Setter
    private String message;
}
