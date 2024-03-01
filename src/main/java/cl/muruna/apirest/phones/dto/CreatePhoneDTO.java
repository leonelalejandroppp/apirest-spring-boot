package cl.muruna.apirest.phones.dto;

import cl.muruna.apirest.phones.entities.PhoneEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePhoneDTO {
    @NotNull(message = "El número no puede ser nulo")
    @NotEmpty(message = "El número no puede ser vacío")
    private String number;

    @NotNull(message = "El código de ciudad no puede ser nulo")
    @NotEmpty(message = "El código de ciudad no puede ser vacío")
    private String cityCode;

    @NotNull(message = "El código de país no puede ser nulo")
    @NotEmpty(message = "El código de país no puede ser vacío")
    private String countryCode;

    public PhoneEntity toPhoneEntity () {
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setNumber(this.getNumber());
        phoneEntity.setCityCode(this.getCityCode());
        phoneEntity.setCountryCode(this.getCountryCode());
        return phoneEntity;
    }
}
