package cl.muruna.apirest.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @Email(message = "Email no válido")
    @NotNull(message = "El campo email es obligatorio")
    @NotEmpty(message = "El campo email no puede estar vacío")
    private String email;

    @NotNull(message = "El campo password es obligatorio")
    @NotEmpty(message = "El campo password no puede estar vacío")
    private String password;
}
