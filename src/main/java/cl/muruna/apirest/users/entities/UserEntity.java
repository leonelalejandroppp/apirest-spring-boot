package cl.muruna.apirest.users.entities;

import cl.muruna.apirest.phones.entities.PhoneEntity;
import cl.muruna.apirest.users.dto.UserCreatedDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;

    private boolean isActive;

    public UserCreatedDTO toCreatedResponse () {
        UserCreatedDTO userCreatedDTO = new UserCreatedDTO();
        userCreatedDTO.setId(this.getId().toString());
        userCreatedDTO.setCreated(this.getCreated());
        userCreatedDTO.setModified(this.getModified());
        userCreatedDTO.setLastLogin(this.getCreated());
        userCreatedDTO.setToken("");
        userCreatedDTO.setIsActive(this.isActive());
        return userCreatedDTO;
    }

}
