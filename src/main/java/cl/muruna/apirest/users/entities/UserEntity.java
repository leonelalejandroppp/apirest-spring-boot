package cl.muruna.apirest.users.entities;

import cl.muruna.apirest.phones.entities.PhoneEntity;
import cl.muruna.apirest.users.dto.UserCreatedDTO;
import cl.muruna.apirest.users.dto.UserListDTO;
import cl.muruna.apirest.users.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity implements UserDetails {
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

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserCreatedDTO toCreatedResponse () {
        return UserCreatedDTO.builder()
                .id(this.getId().toString())
                .created(this.getCreated())
                .modified(this.getModified())
                .lastLogin(this.getCreated())
                .token("")
                .isActive(this.isActive())
                .build();
    }

    public UserListDTO toListDTOResponse () {
        return UserListDTO.builder()
                .id(this.getId().toString())
                .email(this.getEmail())
                .name(this.getName())
                .phones(this.getPhones())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
