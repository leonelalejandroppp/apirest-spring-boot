package cl.muruna.apirest.users.controllers;

import cl.muruna.apirest.users.dto.CreateUserDTO;
import cl.muruna.apirest.users.dto.UserCreatedDTO;
import cl.muruna.apirest.users.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid CreateUserDTO createUserDTO) {
        UserCreatedDTO userCreatedDTO = this.userService.createUser(createUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreatedDTO);
    }
}
