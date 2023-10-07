package com.thoughtworks.capability.gtb.bspringsecuritydemo.controller.dto;

import com.thoughtworks.capability.gtb.bspringsecuritydemo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String username;

    private Boolean enabled;

    public static UserResponseDto from(User user) {
        return new UserResponseDto(user.getUsername(), user.getEnabled());
    }
}
