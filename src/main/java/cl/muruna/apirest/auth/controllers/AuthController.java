package cl.muruna.apirest.auth.controllers;

import cl.muruna.apirest.auth.dto.LoginDTO;
import cl.muruna.apirest.auth.dto.LoginResponseDTO;
import cl.muruna.apirest.auth.dto.RegisterDTO;
import cl.muruna.apirest.auth.services.AuthService;
import cl.muruna.apirest.users.dto.UserCreatedDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseEntity.ok(this.authService.login(loginDTO));
    }

    @PostMapping("register")
    public ResponseEntity<UserCreatedDTO> register(@RequestBody @Valid RegisterDTO registerDTO) {
        return ResponseEntity.ok(this.authService.register(registerDTO));
    }

}
