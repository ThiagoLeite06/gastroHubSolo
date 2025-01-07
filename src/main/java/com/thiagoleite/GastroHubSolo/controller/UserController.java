package com.thiagoleite.GastroHubSolo.controller;

import com.thiagoleite.GastroHubSolo.dto.UserRequestDto;
import com.thiagoleite.GastroHubSolo.dto.UserResponseDto;
import com.thiagoleite.GastroHubSolo.model.User;
import com.thiagoleite.GastroHubSolo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> listUsers(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequestDto userRequestDto) {
        User user = service.create(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
