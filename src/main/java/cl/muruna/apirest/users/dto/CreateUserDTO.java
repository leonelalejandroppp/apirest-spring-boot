package cl.muruna.apirest.users.dto;

import cl.muruna.apirest.phones.dto.CreatePhoneDTO;
import cl.muruna.apirest.users.entities.UserEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    @NotNull(message = "El nombre no puede ser nulo")
    private String name;
    @Email(message = "Email no válido")
    private String email;

    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres y contener al menos una letra mayúscula, una letra minúscula, un número y un carácter especial"
    )
    @NotNull(message = "La contraseña no puede ser nula")
    private String password;

    @Valid
    @NotNull(message = "Los teléfonos no pueden ser nulos")
    private List<CreatePhoneDTO> phones;

    public UserEntity toUserEntity () {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(this.getEmail());
        userEntity.setPassword(this.getPassword());
        userEntity.setName(this.getName());
        return userEntity;
    }
}
