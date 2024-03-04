package cl.muruna.apirest.users.services;

import cl.muruna.apirest.auth.dto.RegisterDTO;
import cl.muruna.apirest.phones.dto.CreatePhoneDTO;
import cl.muruna.apirest.phones.entities.PhoneEntity;
import cl.muruna.apirest.phones.services.PhoneService;
import cl.muruna.apirest.users.dto.CreateUserDTO;
import cl.muruna.apirest.users.dto.UserCreatedDTO;
import cl.muruna.apirest.users.dto.UserListDTO;
import cl.muruna.apirest.users.entities.UserEntity;
import cl.muruna.apirest.users.exceptions.EmailExistsException;
import cl.muruna.apirest.users.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PhoneService phoneService;
    private final PasswordEncoder passwordEncoder;

    public List<UserListDTO> getAllUsers () {
        List<UserEntity> list = this.userRepository.findAll();
        List<UserListDTO> listDTOS = this.getListUserDTO(list);
        return listDTOS;
    }

    private List<UserListDTO> getListUserDTO (List<UserEntity> listEntities) {
        List<UserListDTO> list = new ArrayList<UserListDTO>();
        for (UserEntity userEntity : listEntities) {
            list.add(userEntity.toListDTOResponse());
        }
        return list;
    }

    public UserEntity createUser (RegisterDTO registerDTO) {
        this.alreadyExistsEmail(registerDTO.getEmail());
        UserEntity userEntity = registerDTO.toUserEntity();
        userEntity.setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));
        this.userRepository.save(userEntity);
        if(registerDTO.getPhones() != null){
            for(CreatePhoneDTO createPhoneDTO : registerDTO.getPhones()) {
                PhoneEntity phoneEntity = createPhoneDTO.toPhoneEntity();
                phoneEntity.setUserEntity(userEntity);
                this.phoneService.createPhone(phoneEntity);
            }
        }
        return userEntity;
    }

    private void alreadyExistsEmail (String email) {
        Boolean exists = this.userRepository.findByEmail(email).isPresent();
        if(exists){
            throw new EmailExistsException("El email ya está registrado");
        }
    }

    public UserEntity findByEmail (String email) {

        return this.userRepository.findByEmail(email).orElseThrow();
    }
}
