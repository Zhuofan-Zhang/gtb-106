package com.thoughtworks.capability.gtb.bspringsecuritydemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestDto {
    private String username;

    private String password;
}
