package com.thiagoleite.GastroHubSolo.entrypoint.controllers;

import com.thiagoleite.GastroHubSolo.core.domain.UserDomain;
import com.thiagoleite.GastroHubSolo.core.usecase.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            UpdateUserUseCase updateUserUseCase,
            DeleteUserUseCase deleteUserUseCase,
            GetUserUseCase getUserUseCase,
            ChangePasswordUseCase changePasswordUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.changePasswordUseCase = changePasswordUseCase;
    }

    @PostMapping
    public ResponseEntity<UserDomain> createUser(@RequestBody UserDomain userDomain) {
        UserDomain createdUserDomain = createUserUseCase.execute(userDomain);
        return ResponseEntity.ok(createdUserDomain);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDomain> updateUser(@PathVariable Long id, @RequestBody UserDomain userDomain) {
        UserDomain updatedUserDomain = updateUserUseCase.execute(id, userDomain);
        return ResponseEntity.ok(updatedUserDomain);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDomain> getUserById(@PathVariable Long id) {
        UserDomain userDomain = getUserUseCase.getById(id);
        return ResponseEntity.ok(userDomain);
    }

    @GetMapping
    public ResponseEntity<List<UserDomain>> getAllUsers() {
        List<UserDomain> userDomains = getUserUseCase.getAll();
        return ResponseEntity.ok(userDomains);
    }
}
