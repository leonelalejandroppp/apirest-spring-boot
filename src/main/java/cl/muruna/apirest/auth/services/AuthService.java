package cl.muruna.apirest.auth.services;

import cl.muruna.apirest.auth.dto.LoginDTO;
import cl.muruna.apirest.auth.dto.LoginResponseDTO;
import cl.muruna.apirest.auth.dto.RegisterDTO;
import cl.muruna.apirest.common.jwt.JWTService;
import cl.muruna.apirest.users.dto.UserCreatedDTO;
import cl.muruna.apirest.users.entities.UserEntity;
import cl.muruna.apirest.users.repositories.UserRepository;
import cl.muruna.apirest.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JWTService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public UserCreatedDTO register (RegisterDTO registerDTO) {
        UserEntity userEntity = this.userService.createUser(registerDTO);
        String token = jwtService.createToken(userEntity);
        UserCreatedDTO userCreatedDTO = userEntity.toCreatedResponse();
        userCreatedDTO.setToken(token);
        return userCreatedDTO;
    }

    public LoginResponseDTO login (LoginDTO loginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        UserDetails user = this.userService.findByEmail(loginDTO.getEmail());
        String token = jwtService.createToken(user);
        return LoginResponseDTO.builder()
                .token(token)
                .build();
    }


}
