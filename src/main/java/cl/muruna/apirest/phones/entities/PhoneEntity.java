package cl.muruna.apirest.phones.entities;

import cl.muruna.apirest.users.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class PhoneEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String number;
    private String countryCode;
    private String cityCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity userEntity;
}
