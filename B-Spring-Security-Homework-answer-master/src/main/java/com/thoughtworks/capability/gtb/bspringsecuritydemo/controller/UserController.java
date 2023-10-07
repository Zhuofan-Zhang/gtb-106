package com.thoughtworks.capability.gtb.bspringsecuritydemo.controller;

import com.thoughtworks.capability.gtb.bspringsecuritydemo.controller.dto.UpdateUserRequestDto;
import com.thoughtworks.capability.gtb.bspringsecuritydemo.controller.dto.RegisterUserRequestDto;
import com.thoughtworks.capability.gtb.bspringsecuritydemo.controller.dto.UserResponseDto;
import com.thoughtworks.capability.gtb.bspringsecuritydemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN', 'NORMAL')")
    @PostFilter("hasRole('ADMIN') or filterObject.username == authentication.name")
    public List<UserResponseDto> listUser() {
        return userService.findAll().stream().map(UserResponseDto::from).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody RegisterUserRequestDto requestDto) {
        userService.registerUser(requestDto.getUsername(), requestDto.getPassword());
    }

    @PutMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') and #username != authentication.name")
    public void updateUser(@PathVariable("username") String username, @RequestBody UpdateUserRequestDto requestDto) {
        userService.updateUser(username, requestDto.getEnabled());
    }

}
